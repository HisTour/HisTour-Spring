package trible.histour.application.port.input.dto.response.attraction;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "추천 여행지 목록 조회 응답")
public record AttractionsResponse(
		@Schema(description = "추천 여행지 정보 목록")
		List<AttractionResponse> attractions
) {
}
