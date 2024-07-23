package trible.histour.input.http.common.dto;


import lombok.NonNull;

public interface BaseResponse {
	boolean success();

	@NonNull
	String message();
}
