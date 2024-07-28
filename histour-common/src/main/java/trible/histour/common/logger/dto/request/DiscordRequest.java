package trible.histour.common.logger.dto.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;

@Builder(access = AccessLevel.PRIVATE)
public record DiscordRequest(
	String content,
	List<Embed> embeds
) {

	public static DiscordRequest of(LoggerRequest request) {
		return DiscordRequest.builder()
			.content("# " + request.title())
			.embeds(List.of(Embed.of(request)))
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record Embed(
		String title,
		String description
	) {

		private static Embed of(LoggerRequest request) {
			return Embed.builder()
				.title("요청 URI: " + request.requestUri())
				.description(content(request))
				.build();
		}

		private static String content(LoggerRequest request) {
			return "### 발생시각: \n"
				+ dateTimeToString(request.sendAt())
				+ "\n### 에러 내용: \n"
				+ request.content();
		}

		private static String dateTimeToString(LocalDateTime dateTime) {
			val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return dateTime.format(formatter);
		}
	}
}
