package trible.histour.common.logger.discord;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import trible.histour.common.logger.HookLogger;
import trible.histour.common.logger.dto.request.DiscordRequest;
import trible.histour.common.logger.dto.request.LoggerRequest;

@Component
@Slf4j
public class DiscordWebhookLogger implements HookLogger {
    @Value("${discord.webhook.url}")
    private String webhookUrl;

    private static final String APPLICATION_JSON_UTF8_VALUE = "application/json; UTF-8";

    @Override
    public void send(LoggerRequest request) {
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
}
