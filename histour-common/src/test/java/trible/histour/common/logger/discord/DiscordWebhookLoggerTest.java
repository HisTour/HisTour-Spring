package trible.histour.common.logger.discord;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import lombok.val;
import trible.histour.common.logger.dto.request.LoggerRequest;

@Disabled
@SpringBootTest
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {TestConfiguration.class})
class DiscordWebhookLoggerTest {
	@Autowired
	DiscordWebhookLogger discordWebhookLogger;

	@Test
	void sendExceptionErrorLogMessageToDiscord() {
		val request = LoggerRequest.of("test-uri", "TEST", "테스트 알림입니다.");
		discordWebhookLogger.sendException(request);
	}
}
