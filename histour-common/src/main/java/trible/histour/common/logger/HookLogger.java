package trible.histour.common.logger;

import trible.histour.common.logger.dto.request.LoggerRequest;

public interface HookLogger {
	void send(LoggerRequest request);
}
