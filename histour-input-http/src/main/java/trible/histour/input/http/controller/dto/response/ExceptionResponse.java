package trible.histour.input.http.controller.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
	private final String message;
	private final String cause;
	private final LocalDateTime timestamp;

	public ExceptionResponse(String message) {
		this.message = message;
		this.cause = "";
		this.timestamp = LocalDateTime.now();
	}

	public ExceptionResponse(String message, String cause) {
		this.message = message;
		this.cause = cause;
		this.timestamp = LocalDateTime.now();
	}
}
