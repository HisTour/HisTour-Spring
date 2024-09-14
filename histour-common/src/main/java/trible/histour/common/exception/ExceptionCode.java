package trible.histour.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
	// 4xx
	UNAUTHORIZED(401, "유효하지 않은 토큰"),
	BAD_REQUEST(400, "유효하지 않은 요청"),
	NOT_UNLOCK_MISSION(400, "해금하지 않은 미션"),
	NO_CHARACTER(400, "캐릭터 설정이 필요한 회원"),
	NOT_FOUND(404, "존재하지 않는 자원"),

	// 5xx
	INTERNAL_SERVER_ERROR(500, "서버 내부 오류"),
	SERVICE_AVAILABLE(503, "서비스에 접근할 수 없음");

	private final int statusCode;
	private final String message;
}
