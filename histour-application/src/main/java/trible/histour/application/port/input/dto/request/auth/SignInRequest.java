package trible.histour.application.port.input.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import trible.histour.application.domain.member.SocialType;

@Schema(description = "소셜 로그인 요청 정보")
public record SignInRequest(
	@Schema(description = "로그인하려는 소셜 서비스", example = "KAKAO")
	SocialType type
) {
}
