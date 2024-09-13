package trible.histour.input.http.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import trible.histour.common.exception.HistourException;
import trible.histour.common.logger.HookLogger;
import trible.histour.common.logger.dto.request.LoggerRequest;
import trible.histour.input.http.controller.dto.response.ExceptionResponse;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionAdvice {
	private final HookLogger hookLogger;

	@ExceptionHandler(HistourException.class)
	public ResponseEntity<ExceptionResponse> handleHistourException(HistourException exception) {
		log.error(exception.getMessage());
		return ResponseEntity
			.status(exception.statusCode)
			.body(
				ExceptionResponse.builder()
					.code(exception.exceptionCode)
					.message(exception.defaultMessage)
					.cause(exception.detailMessage)
					.timestamp(LocalDateTime.now())
					.build()
			);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception, WebRequest webRequest) {
		log.error(exception.getMessage());
		hookLogger.sendException(LoggerRequest.error(exception, requestUri(webRequest)));
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(
				ExceptionResponse.builder()
					.message("예기치 못한 에러가 발생했습니다.")
					.timestamp(LocalDateTime.now())
					.build()
			);
	}

	private String requestUri(WebRequest webRequest) {
		val request = ((ServletWebRequest)webRequest).getRequest();
		val path = request.getMethod() + " " + request.getRequestURL();
		val queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
		return path + queryString;
	}
}
