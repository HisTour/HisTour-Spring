package trible.histour.input.http.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import trible.histour.common.exception.HistourException;
import trible.histour.input.http.controller.dto.response.ExceptionResponse;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

		@ExceptionHandler(HistourException.class)
		public ResponseEntity<ExceptionResponse> handleHistourException(HistourException exception) {
				log.error(exception.getMessage());
				return ResponseEntity
								.status(exception.statusCode)
								.body(new ExceptionResponse(exception.defaultMessage, exception.detailMessage));
		}

		@ExceptionHandler(RuntimeException.class)
		public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception) {
				log.error(exception.getMessage());
				return ResponseEntity
								.status(HttpStatus.INTERNAL_SERVER_ERROR)
								.body(new ExceptionResponse(exception.getMessage()));
		}
}
