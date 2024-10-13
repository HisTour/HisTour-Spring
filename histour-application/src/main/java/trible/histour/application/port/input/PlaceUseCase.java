package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.response.place.PlacesResponse;

public interface PlaceUseCase {
	PlacesResponse getPlaces(long memberId);

	void recommendPlace(long memberId, String content);
}
