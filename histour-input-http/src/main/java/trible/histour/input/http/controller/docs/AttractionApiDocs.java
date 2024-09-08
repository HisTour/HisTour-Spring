package trible.histour.input.http.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.response.attraction.AttractionsResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "AttractionApi", description = "추천 여행지 관련 api")
public interface AttractionApiDocs {
	@Operation(
		summary = "추천 여행지 목록 조회 api",
		description = "추천 여행지 목록을 조회합니다."
	)
	SuccessResponse<AttractionsResponse> getAttractions();
}
