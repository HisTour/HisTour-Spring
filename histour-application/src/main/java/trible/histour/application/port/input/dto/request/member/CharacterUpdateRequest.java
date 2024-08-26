package trible.histour.application.port.input.dto.request.member;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "캐릭터 설정 요청 정보")
public record CharacterUpdateRequest(
	@Schema(description = "설정하려는 캐릭터 id", example = "1")
	long characterId
) {
}
