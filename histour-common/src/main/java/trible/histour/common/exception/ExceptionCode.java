package trible.histour.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
		// 4xx
		UNAUTHORIZED(401, "유효하지 않은 토큰"),
		BAD_REQUEST(400, "유효하지 않은 요청"),
		NOT_FOUND(404, "존재하지 않는 자원"),

		// 5xx
		SERVER_INTERNAL_ERROR(500, "서버 내부 오류"),
		SERVICE_AVAILABLE(503, "서비스에 접근할 수 없음"),
		;

		private final int statusCode;
		private final String message;
}
