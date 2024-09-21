package trible.histour.input.http.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.request.assistant.ChatAssistantRequest;
import trible.histour.application.port.input.dto.response.assistant.ChatAssistantResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "ChatAssistantApi", description = "챗봇 관련 API")
public interface AssistantApiDocs {
	@Operation(
			summary = "챗봇 세션 생성 api",
			description = "챗봇 서버 세션을 생성합니다.",
			responses = {
				@ApiResponse(responseCode = "201", description = "CREATED success")
			}
	)
	SuccessResponse<ChatAssistantResponse> getChatAssistant(
			@RequestBody(
					description = "챗봇 생성 Request Body",
					required = true,
					content = @Content(schema = @Schema(implementation = ChatAssistantRequest.class))
			) ChatAssistantRequest request
		);
}
