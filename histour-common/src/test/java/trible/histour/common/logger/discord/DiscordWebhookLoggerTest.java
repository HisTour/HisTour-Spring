package trible.histour.common.logger.discord;

import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import trible.histour.common.logger.dto.request.LoggerRequest;

@Disabled
@SpringBootTest
@ActiveProfiles(profiles = {"test", "local"})
@ContextConfiguration(classes = {TestConfiguration.class})
class DiscordWebhookLoggerTest {
    @Autowired
    DiscordWebhookLogger discordWebhookLogger;

    //TODO: 디스코드 webhook-url 생성 후 테스트 예정
    @Test
    void sendErrorLogMessageToDiscord() {
        val request = LoggerRequest.of("test-uri", "TEST", "테스트 알림입니다.");
        discordWebhookLogger.send(request);
    }
}
