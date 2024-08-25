package trible.histour.application.domain.quiz;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionType;
import trible.histour.application.port.input.QuizUseCase;
import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;
import trible.histour.application.port.output.persistence.MemberMissionPort;
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

	@Transactional
	@Override
	public QuizzesResponse getQuizzes(long memberId, long missionId) {
		val mission = missionPort.findById(missionId);
		val missions = missionPort.findAllByPlaceId(mission.getPlaceId());
		val missionIds = missions.stream().map(Mission::getId).toList();
		val memberMissions = memberMissionPort.findAllByMemberIdAndMissionIds(memberId, missionIds);
		validMemberQuiz(memberId, mission, memberMissions, missions);

		memberMissions.stream()
			.filter(memberMission -> memberMission.getMissionId() == missionId)
			.findAny()
			.orElseGet(() -> memberMissionPort.save(new MemberMission(memberId, missionId)));

		val quizzes = quizPort.findAllByMissionId(missionId);
		return QuizzesResponse.of(mission, quizzes);
	}

	private void validMemberQuiz(
		long memberId,
		Mission mission,
		List<MemberMission> memberMissions,
		List<Mission> missions
	) {
		val completedMemberMissions = memberMissions.stream().filter(MemberMission::isCompleted).toList();

		completedMemberMissions.stream()
			.filter(it -> it.getMissionId() == mission.getId())
			.findAny()
			.ifPresent(it -> {
				throw new HistourException(
					ExceptionCode.BAD_REQUEST,
					"이미 완료된 미션, MissionID: " + mission.getId() + ", MemberID: " + memberId);
			});

		if (mission.getType() == MissionType.NORMAL) {
			val missionById = missions.stream().collect(Collectors.toMap(Mission::getId, it -> it));
			completedMemberMissions.stream()
				.filter(it -> missionById.get(it.getMissionId()).getType() == MissionType.INTRO)
				.findAny()
				.orElseThrow(() -> new HistourException(
					ExceptionCode.BAD_REQUEST,
					"Intro 미션을 수행하지 않음, MissionID: " + mission.getId() + ", MemberID: " + memberId));
		}

		if (mission.getType() == MissionType.FINAL) {
			val place = placePort.findById(mission.getPlaceId());
			if (completedMemberMissions.size() != place.getRequiredMissionCount() - 1) {
				throw new HistourException(
					ExceptionCode.BAD_REQUEST,
					"Final 미션 전 필요한 완료 미션 개수가 부족함 ("
						+ completedMemberMissions.size() + "/" + (place.getRequiredMissionCount() - 1)
						+ "), MemberID: " + memberId);
			}
		}
	}
}
