package trible.histour.input.http.controller.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionResponse(
	String code,
	String message,
	String cause,
	LocalDateTime timestamp
) {
}
