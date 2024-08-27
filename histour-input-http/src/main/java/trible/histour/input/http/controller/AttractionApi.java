package trible.histour.input.http.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AttractionUseCase;
import trible.histour.application.port.input.dto.response.attraction.AttractionsResponse;
import trible.histour.input.http.controller.docs.AttractionApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attractions")
public class AttractionApi implements AttractionApiDocs {
	private final AttractionUseCase attractionUseCase;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@Override
	public SuccessResponse<AttractionsResponse> getAttractions() {
		val response = attractionUseCase.getAttractions();
		return SuccessResponse.of(response);
	}

}
