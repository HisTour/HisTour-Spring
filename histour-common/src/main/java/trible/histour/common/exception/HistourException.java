package trible.histour.common.exception;

import jakarta.validation.constraints.NotNull;

public class HistourException extends RuntimeException {
		@NotNull
		public final int statusCode;
		@NotNull
		public final String defaultMessage;
		public final String detailMessage;

		public HistourException(ExceptionCode exceptionCode) {
				this.statusCode = exceptionCode.getStatusCode();
				this.defaultMessage = exceptionCode.getMessage();
				this.detailMessage = "";
		}

		public HistourException(ExceptionCode exceptionCode, String detailMessage) {
				this.statusCode = exceptionCode.getStatusCode();
				this.defaultMessage = exceptionCode.getMessage();
				this.detailMessage = detailMessage;
		}
}
