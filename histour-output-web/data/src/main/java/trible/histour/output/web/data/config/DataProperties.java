package trible.histour.output.web.data.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "data")
public record DataProperties(
		Holiday holiday,
		Attraction attraction,
		ChatAssistant chatAssistant
) {
	public record Holiday(
			String api,
			String secret
	) {
	}

	public record Attraction(
			String api,
			String serviceKey,
			String mapX,
			String mapY,
			String radius
	) {
	}

	public record ChatAssistant(
			String aiApi
	) {
	}
}
