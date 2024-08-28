package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.response.attraction.AttractionsResponse;

public interface AttractionUseCase {
	AttractionsResponse getAttractions();
}
