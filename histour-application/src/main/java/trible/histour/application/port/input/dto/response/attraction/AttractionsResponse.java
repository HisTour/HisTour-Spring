package trible.histour.application.port.input.dto.response.attraction;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.attraction.Attraction;


@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "추천 여행지 목록 조회 응답")
public record AttractionsResponse(
		@Schema(description = "추천 여행지 정보 목록")
		List<AttractionResponse> attractions
) {
	public static AttractionsResponse of(
			List<Attraction> attractions
	) {
		return AttractionsResponse.builder()
				.attractions(toAttractions(attractions))
				.build();
	}

	private static List<AttractionResponse> toAttractions(
			List<Attraction> attractions
	) {
		return attractions.stream().map(AttractionResponse::of)
						.toList();
	}
}
