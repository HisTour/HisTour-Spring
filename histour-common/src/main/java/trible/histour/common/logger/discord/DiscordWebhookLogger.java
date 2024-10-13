package trible.histour.common.logger.discord;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;
import trible.histour.common.logger.HookLogger;
import trible.histour.common.logger.dto.request.DiscordRequest;
import trible.histour.common.logger.dto.request.LoggerRequest;

@Component
@Slf4j
public class DiscordWebhookLogger implements HookLogger {
	@Value("${discord.webhook.url}")
	private String webhookUrl;

	@Value("${discord.webhook.recommend_place_url}")
	private String webhookRecommendPlaceUrl;

	private static final String APPLICATION_JSON_UTF8_VALUE = "application/json; UTF-8";

	@Override
	public void sendException(LoggerRequest request) {
		try {
			RestClient.create()
				.post()
				.uri(webhookUrl)
				.header(HttpHeaders.ACCEPT, APPLICATION_JSON_UTF8_VALUE)
				.header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
				.body(DiscordRequest.of(request))
				.retrieve();
		} catch (RuntimeException exception) {
			log.error("Discord Error", exception);
		}
	}

	@Override
	public void recommendPlace(long memberId, String username, String content) {
		try {
			RestClient.create()
				.post()
				.uri(webhookRecommendPlaceUrl)
				.header(HttpHeaders.ACCEPT, APPLICATION_JSON_UTF8_VALUE)
				.header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
				.body(DiscordRequest.of(
					"여행지 추천 알림",
					"[MemberID: " + memberId + "] " + username,
					content))
				.retrieve();
		} catch (RuntimeException exception) {
			log.error("Discord Error", exception);
		}
	}
}
