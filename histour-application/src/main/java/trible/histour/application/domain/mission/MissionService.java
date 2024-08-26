package trible.histour.application.domain.mission;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.quiz.Quiz;
import trible.histour.application.port.input.MissionUseCase;
import trible.histour.application.port.input.dto.response.mission.MissionsResponse;
import trible.histour.application.port.output.persistence.MemberMissionPort;
import trible.histour.application.port.output.persistence.MemberQuizPort;
import trible.histour.application.port.output.persistence.MissionPort;
import trible.histour.application.port.output.persistence.PlacePort;
import trible.histour.application.port.output.persistence.QuizPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService implements MissionUseCase {
	private final MissionPort missionPort;
	private final MemberMissionPort memberMissionPort;
	private final QuizPort quizPort;
	private final MemberQuizPort memberQuizPort;
	private final PlacePort placePort;

	@Override
	public MissionsResponse getMissions(long memberId, long placeId) {
		val place = placePort.findById(placeId);

		val missions = missionPort.findAllByPlaceId(placeId);
		val missionIds = missions.stream().map(Mission::getId).toList();
		val memberMissions = memberMissionPort.findAllByMemberIdAndMissionIds(memberId, missionIds);

		val memberMissionByMissionId = memberMissions.stream()
			.collect(Collectors.toMap(MemberMission::getMissionId, memberMission -> memberMission));

		val sortedMissions = missions.stream()
			.sorted(Comparator.comparing((Mission mission) ->
					memberMissionByMissionId.get(mission.getId()), // 1. MemberMission 존재하면 우선으로 정렬
				Comparator.nullsLast(Comparator.comparing(MemberMission::getCreatedAt)))) // 2. createdAt 기준 정렬
			.toList();

		val quizzes = quizPort.findAllByMissionIds(missionIds);
		val quizById = quizzes.stream().collect(Collectors.toMap(Quiz::getId, quiz -> quiz));
		val quizIds = quizzes.stream().map(Quiz::getId).toList();
		val memberQuizzes = memberQuizPort.findAllByMemberIdAndQuizIds(memberId, quizIds);

		return MissionsResponse.of(
			place,
			sortedMissions,
			memberMissionByMissionId,
			quizzes.stream().collect(Collectors.groupingBy(Quiz::getMissionId)),
			memberQuizzes.stream().collect(Collectors.groupingBy(memberQuiz -> {
				val quiz = quizById.get(memberQuiz.getQuizId());
				return quiz.getMissionId();
			}))
		);
	}
}
