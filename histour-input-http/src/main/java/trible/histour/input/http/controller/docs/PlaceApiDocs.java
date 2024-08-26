package trible.histour.input.http.controller.docs;

import java.security.Principal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.response.place.PlacesResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "PlaceApi", description = "여행지 관련 api")
public interface PlaceApiDocs {

	@Operation(
		summary = "여행지 목록 조회 api",
		description = "여행지 정보 목록을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success")
		}
	)
	SuccessResponse<PlacesResponse> getPlaces(
		@Parameter(hidden = true) Principal principal
	);
}
