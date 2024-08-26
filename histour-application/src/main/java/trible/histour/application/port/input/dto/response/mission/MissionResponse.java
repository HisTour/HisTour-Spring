package trible.histour.application.port.input.dto.response.mission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionState;
import trible.histour.application.domain.mission.MissionType;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "미션 조회 정보 응답")
public record MissionResponse(
	@Schema(description = "미션 id", example = "1")
	long id,
	@Schema(description = "미션 상태", example = "PROGRESS")
	MissionState state,
	@Schema(description = "미션 이름", example = "화성행궁 - 천자의 명에 따라")
	String name,
	@Schema(description = "미션 타입", example = "NORMAL")
	MissionType type,
	@Schema(description = "수행한 퀴즈 개수", example = "3")
	int clearedQuizCount,
	@Schema(description = "전체 퀴즈 개수", example = "6")
	int totalQuizCount
) {

	public static MissionResponse of(
		Mission mission,
		MemberMission memberMission,
		int clearedQuizCount,
		int totalQuizCount
	) {
		return MissionResponse.builder()
			.id(mission.getId())
			.state(memberMission.getState())
			.clearedQuizCount(clearedQuizCount)
			.totalQuizCount(totalQuizCount)
			.build();
	}
}
