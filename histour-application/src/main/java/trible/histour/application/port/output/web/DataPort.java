package trible.histour.application.port.output.web;

import trible.histour.application.port.output.web.dto.request.ChatAssistantAiRequest;
import trible.histour.application.port.output.web.dto.response.ChatAssistantAiResponse;
import trible.histour.application.port.output.web.dto.response.DataAttractionResponse;
import trible.histour.application.port.output.web.dto.response.DataHolidayResponse;

public interface DataPort {
	DataHolidayResponse getHoliday();

	DataAttractionResponse getAttraction();

	ChatAssistantAiResponse getChatAssistant(ChatAssistantAiRequest chatAssistantAiRequest);
}
