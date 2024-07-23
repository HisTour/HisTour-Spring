package trible.histour.input.http.controller.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SuccessResponse<T>(
		T data
) {
	public static <T> SuccessResponse<T> of(T data) {
		return SuccessResponse.<T>builder()
				.data(data)
				.build();
	}
}
