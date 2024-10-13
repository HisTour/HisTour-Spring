package trible.histour.application.port.input.dto.request.place;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "여행지 추천 정보 요청")
public record PlaceRecommendRequest(
	@Schema(description = "여행지 추천 내용", example = "대구 추천해요!")
	String content
) {
}
