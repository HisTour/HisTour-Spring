package trible.histour.application.domain.mission;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.quiz.Quiz;
import trible.histour.application.port.input.MissionUseCase;
import trible.histour.application.port.input.dto.request.mission.UpdatedMissionsRequest;
import trible.histour.application.port.input.dto.response.mission.MissionsResponse;
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

	@Transactional
	@Override
	public void completeMemberMission(long memberId, UpdatedMissionsRequest request) {
		val completeMissionId = request.completedMissionId();
		if (completeMissionId != null) {
			val completedMemberMission = memberMissionPort.findByMemberIdAndMissionId(memberId, completeMissionId);
			completedMemberMission.complete();
			memberMissionPort.update(completedMemberMission);
		}

		val nextMissionId = request.nextMissionId();
		if (nextMissionId != null) {
			val mission = missionPort.findById(nextMissionId);
			val missions = missionPort.findAllByPlaceId(mission.getPlaceId());
			val missionIds = missions.stream().map(Mission::getId).toList();
			val memberMissions = memberMissionPort.findAllByMemberIdAndMissionIds(memberId, missionIds);
			validMemberQuiz(memberId, mission, memberMissions, missions);
			memberMissionPort.save(memberId, nextMissionId);
		}
	}

	private void validMemberQuiz(
		long memberId,
		Mission mission,
		List<MemberMission> memberMissions,
		List<Mission> missions
	) {
		memberMissions.stream()
			.filter(it -> it.getMissionId() == mission.getId())
			.findAny()
			.ifPresent(it -> {
				throw new HistourException(
					ExceptionCode.BAD_REQUEST,
					"이미 해금한 미션, MissionID: " + mission.getId() + ", MemberID: " + memberId);
			});

		val completedMemberMissions = memberMissions.stream().filter(MemberMission::isCompleted).toList();

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
