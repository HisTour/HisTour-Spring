package trible.histour.application.port.input.dto.response.assistant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "챗봇 생성 응답")
public record ChatAssistantResponse(
		@Schema(description = "챗봇 세션 url", example = "http://***")
		String url
) {
	public static ChatAssistantResponse of(String url) {
		return ChatAssistantResponse.builder()
				.url(url)
				.build();
	}
}
