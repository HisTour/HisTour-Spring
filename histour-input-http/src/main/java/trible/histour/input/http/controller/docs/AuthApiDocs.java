package trible.histour.input.http.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
		description = "로그인을 진행합니다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "CREATED success")
		}
	)
	SuccessResponse<SignInResponse> signIn(
		@Parameter(
			name = "Authorization",
			description = "소셜서비스 액세스 토큰",
			required = true,
			in = ParameterIn.HEADER
		) String socialAccessToken,
		@RequestBody(
			description = "로그인 Request Body",
			required = true,
			content = @Content(schema = @Schema(implementation = SignInRequest.class))
		) SignInRequest request
	);
}
