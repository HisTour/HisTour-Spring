package trible.histour.application.port.input.dto.response.mission;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.place.Place;
import trible.histour.application.domain.quiz.MemberQuiz;
import trible.histour.application.domain.quiz.Quiz;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "미션 목록 조회 정보 응답")
public record MissionsResponse(
	@Schema(description = "필수 미션 수행 개수", example = "1")
	int requiredMissionCount,
	@Schema(description = "미션 정보 목록")
	List<MissionResponse> missions
) {

	public static MissionsResponse of(
		Place place,
		List<Mission> missions,
		Map<Long, MemberMission> memberMissionByMissionId,
		Map<Long, List<Quiz>> quizzesByMissionId,
		Map<Long, List<MemberQuiz>> memberQuizzesByMissionId
	) {
		return MissionsResponse.builder()
			.requiredMissionCount(place.getRequiredMissionCount())
			.missions(toMissions(missions, memberMissionByMissionId, quizzesByMissionId, memberQuizzesByMissionId))
			.build();
	}

	private static List<MissionResponse> toMissions(
		List<Mission> missions,
		Map<Long, MemberMission> memberMissionByMissionId,
		Map<Long, List<Quiz>> quizzesByMissionId,
		Map<Long, List<MemberQuiz>> memberQuizzesByMissionId
	) {
		return missions.stream().map(mission ->
				MissionResponse.of(
					mission,
					memberMissionByMissionId.get(mission.getId()),
					memberQuizzesByMissionId.get(mission.getId()),
					quizzesByMissionId.get(mission.getId())))
			.toList();
	}
}
