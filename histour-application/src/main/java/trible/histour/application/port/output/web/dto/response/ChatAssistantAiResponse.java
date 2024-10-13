package trible.histour.application.port.output.web.dto.response;

public record ChatAssistantAiResponse(
		Data data
) {
	public record Data(
			String url
	) {
	}
}
