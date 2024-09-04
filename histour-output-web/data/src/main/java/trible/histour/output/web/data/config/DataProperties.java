package trible.histour.output.web.data.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "data")
public record DataProperties(
	Holiday holiday
) {
	public record Holiday(
		String api,
		String secret
	) {
	}
}
