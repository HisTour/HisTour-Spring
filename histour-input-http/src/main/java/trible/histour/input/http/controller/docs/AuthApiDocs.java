package trible.histour.input.http.controller.docs;

import java.security.Principal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.request.auth.SignInRequest;
import trible.histour.application.port.input.dto.response.auth.SignInResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "AuthApi", description = "인증 관련 api")
public interface AuthApiDocs {

	@Operation(
		summary = "로그인 api",
		description = "로그인을 진행합니다. 스웨거 상단 오른쪽 Authorize 내 소셜 서비스 액세스 토큰 또는 임의 문자열 추가 후 실행해주세요.",
		responses = {
			@ApiResponse(responseCode = "201", description = "CREATED success")
		}
	)
	SuccessResponse<SignInResponse> signIn(
		@Parameter(hidden = true) String socialAccessToken,
		@RequestBody(
			description = "로그인 Request Body",
			required = true,
			content = @Content(schema = @Schema(implementation = SignInRequest.class))
		) SignInRequest request
	);

	@Operation(
		summary = "액세스 토큰 재발급 api",
		description = "액세스 토큰을 재발급합니다. 스웨거 상단 오른쪽 Authorize 내 리프레시 토큰(로그인 때 발급) 추가 후 실행해주세요.",
		responses = {
			@ApiResponse(responseCode = "201", description = "CREATED success")
		}
	)
	SuccessResponse<SignInResponse> reissueAccessToken(
		@Parameter(hidden = true) Principal principal
	);
}
