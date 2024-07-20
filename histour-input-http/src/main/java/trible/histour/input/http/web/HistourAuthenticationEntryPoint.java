package trible.histour.input.http.web;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.input.http.controller.dto.response.ExceptionResponse;

@Component
public class HistourAuthenticationEntryPoint implements AuthenticationEntryPoint {
		private final ObjectMapper objectMapper = new ObjectMapper();

		@Override
		public void commence(
						HttpServletRequest request,
						HttpServletResponse response,
						AuthenticationException authException
		) throws IOException {
				response.setCharacterEncoding("UTF-8");
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().println(objectMapper.writeValueAsString(exceptionResponse()));
		}

		private ResponseEntity<ExceptionResponse> exceptionResponse() {
				return ResponseEntity
								.status(ExceptionCode.UNAUTHORIZED.getStatusCode())
								.body(ExceptionResponse.of(ExceptionCode.UNAUTHORIZED.getMessage()));
		}
}