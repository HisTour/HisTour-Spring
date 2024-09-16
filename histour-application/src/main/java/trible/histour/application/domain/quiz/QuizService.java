package trible.histour.application.domain.quiz;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionState;
import trible.histour.application.port.input.QuizUseCase;
import trible.histour.application.port.input.dto.request.quiz.QuizGradeRequest;
import trible.histour.application.port.input.dto.response.quiz.QuizGradeResponse;
import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;
import trible.histour.application.port.output.persistence.MemberMissionPort;
import trible.histour.application.port.output.persistence.MemberQuizPort;
import trible.histour.application.port.output.persistence.MissionPort;
import trible.histour.application.port.output.persistence.PlacePort;
import trible.histour.application.port.output.persistence.QuizPort;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService implements QuizUseCase {
	private final QuizPort quizPort;
	private final MissionPort missionPort;
	private final PlacePort placePort;
	private final MemberMissionPort memberMissionPort;
	private final MemberQuizPort memberQuizPort;

	@Transactional
	@Override
	public QuizzesResponse getQuizzes(long memberId, long missionId) {
		val mission = missionPort.findById(missionId);
		if (!memberMissionPort.isExistByMemberIdAndMissionId(memberId, missionId)) {
			throw new HistourException(
				ExceptionCode.NOT_UNLOCK_MISSION,
				"MissionID: " + mission.getId() + ", MemberID: " + memberId);
		}
		val quizzes = quizPort.findAllByMissionId(missionId);
		return QuizzesResponse.of(mission, quizzes);
	}

	@Transactional
	@Override
	public QuizGradeResponse gradeQuiz(long memberId, QuizGradeRequest quizGradeRequest) {
		val quiz = quizPort.findById(quizGradeRequest.quizId());
		val quizType = quiz.getType();
		val mission = missionPort.findById(quiz.getMissionId());
		val requiredMissionCount = placePort.findById(mission.getPlaceId()).getRequiredMissionCount();
		val clearMissionCount = getPlaceClearMission(memberId, mission.getPlaceId(), quizGradeRequest.isLastTask());
		val isAnswerCorrect = getIsAnswerCorrect(quizType, quizGradeRequest.memberAnswer(), quiz.answer);
		if (isAnswerCorrect) {
			memberQuizPort.save(new MemberQuiz(memberId, quiz.getId()));
		}
		return QuizGradeResponse.of(isAnswerCorrect, clearMissionCount, requiredMissionCount);
	}

	private Integer getPlaceClearMission(long memberId, long placeId, boolean isLastQuiz) {
		if (!isLastQuiz) {
			return null;
		}
		val placeMissions = missionPort.findAllByPlaceId(placeId);
		val memberCompleteMissions = memberMissionPort.findAllByMemberIdAndState(memberId, MissionState.COMPLETE);
		val placeMissionIds = placeMissions.stream()
			.map(Mission::getId)
			.collect(Collectors.toSet());

		val completeMissionNumber = memberCompleteMissions.stream()
			.filter(memberMission -> placeMissionIds.contains(memberMission.getMissionId()))
			.count();
		return (int)completeMissionNumber;
	}

	private boolean getIsAnswerCorrect(QuizType quizType, String memberAnswer, String answer) {
		switch (quizType) {
			case READING -> {
				return true;
			}
			case KEYWORD -> {
				return memberAnswer.equals(answer);
			}
			default -> throw new HistourException(ExceptionCode.BAD_REQUEST, "해당 유형의 문제는 아직 준비되지 않았습니다.");
		}
	}
}
