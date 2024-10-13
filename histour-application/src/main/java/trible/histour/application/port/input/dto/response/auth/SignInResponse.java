package trible.histour.application.port.input.dto.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.member.Member;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "로그인 완료 정보 응답")
public record SignInResponse(
	@Schema(description = "나들ai 액세스 토큰", example = "ey...")
	String accessToken,
	@Schema(description = "나들ai 리프레시 토큰", example = "ey...")
	String refreshToken,
	@Schema(description = "회원 이름", example = "깨비0")
	String username
) {

	public static SignInResponse of(Member member, String accessToken) {
		return SignInResponse.builder()
			.accessToken(accessToken)
			.refreshToken(member.getRefreshToken())
			.username(member.getUsername())
			.build();
	}
}
