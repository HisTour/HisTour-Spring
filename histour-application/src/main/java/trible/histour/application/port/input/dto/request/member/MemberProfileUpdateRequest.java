package trible.histour.application.port.input.dto.request.member;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "캐릭터 설정 요청 정보")
public record MemberProfileUpdateRequest(
	@Schema(description = "변경하려는 캐릭터 id", example = "1")
	long characterId,
	@Schema(description = "변경하려는 회원 이름", example = "도깨비")
	String username
) {
}
