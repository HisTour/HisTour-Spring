package trible.histour.application.port.input.dto.request.mission;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "미션 해금 요청 정보")
public record UpdatedMissionsRequest(
	@Schema(description = "완료한 서브미션 id", example = "1")
	Long completedMissionId,
	@Schema(description = "선택된 다음 서브미션 id", example = "1")
	Long nextMissionId
) {
}
