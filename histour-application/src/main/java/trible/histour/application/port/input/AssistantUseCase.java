package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.request.assistant.ChatAssistantRequest;
import trible.histour.application.port.input.dto.response.assistant.ChatAssistantResponse;

public interface AssistantUseCase {
	ChatAssistantResponse getChatAssistant(ChatAssistantRequest chatAssistantRequest);
}
