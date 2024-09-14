package trible.histour.application.port.input.dto.request.mission;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "미션 해금 요청 정보")
public record UpdatedMissionsRequest(
	long completedMissionId,
	long nextMissionId
) {
}
