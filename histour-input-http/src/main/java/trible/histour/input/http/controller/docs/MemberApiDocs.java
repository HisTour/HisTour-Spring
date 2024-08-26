package trible.histour.input.http.controller.docs;

import java.security.Principal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.request.member.CharacterUpdateRequest;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "MemberApi", description = "회원 관련 api")
public interface MemberApiDocs {

	@Operation(
		summary = "캐릭터 설정 api",
		description = "회원의 캐릭터를 설정합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "OK success",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
		}
	)
	SuccessResponse<?> updateCharacters(
		@Parameter(hidden = true) Principal principal,
		@RequestBody(
			description = "캐릭터 설정 Request Body",
			required = true,
			content = @Content(schema = @Schema(implementation = CharacterUpdateRequest.class))
		) CharacterUpdateRequest request
	);
}
