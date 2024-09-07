package trible.histour.application.port.input.dto.response.attraction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "추천 여행지 조회 응답")
public record AttractionResponse(
		@Schema(description = "추천 여행지 이름", example = "가족들과 함께 가기 좋은 역사 관광")
		String name,
		@Schema(description = "추천 여행지 본문", example = "아름다운 건축 광화루원에 시작하는 ~")
		String description,
		@Schema(description = "추천 여행지 이미지", example = "url형식")
		String imageUrl
) {
}
