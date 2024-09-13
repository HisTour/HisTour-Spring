package trible.histour.common.logger;

import trible.histour.common.logger.dto.request.LoggerRequest;

public interface HookLogger {
	void sendException(LoggerRequest request);

	void recommendPlace(long memberId, String username, String content);
}
