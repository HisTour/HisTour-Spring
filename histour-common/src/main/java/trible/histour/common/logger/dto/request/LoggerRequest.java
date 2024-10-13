package trible.histour.common.logger.dto.request;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record LoggerRequest(
	String requestUri,
	String title,
	String content,
	LocalDateTime sendAt
) {

	public static LoggerRequest of(String uri, String title, String content) {
		return LoggerRequest.builder()
			.requestUri(uri)
			.title(title)
			.content(content)
			.sendAt(LocalDateTime.now())
			.build();
	}

	public static LoggerRequest error(Exception exception, String uri) {
		return LoggerRequest.builder()
			.requestUri(uri)
			.title("📢 500 ERROR")
			.content(exception.getMessage())
			.sendAt(LocalDateTime.now())
			.build();
	}
}
