package trible.histour.input.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AssistantUseCase;
import trible.histour.application.port.input.dto.request.assistant.ChatAssistantRequest;
import trible.histour.application.port.input.dto.response.assistant.ChatAssistantResponse;
import trible.histour.input.http.controller.docs.AssistantApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/assistant")
public class AssistantApi implements AssistantApiDocs {
	private final AssistantUseCase assistantUseCase;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@Override
	public SuccessResponse<ChatAssistantResponse> getChatAssistant(
			@RequestBody ChatAssistantRequest chatAssistantRequest) {
		val response = assistantUseCase.getChatAssistant(chatAssistantRequest);
		return SuccessResponse.of(response);
	}
}
