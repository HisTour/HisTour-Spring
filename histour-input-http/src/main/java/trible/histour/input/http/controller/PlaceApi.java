package trible.histour.input.http.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.PlaceUseCase;
import trible.histour.application.port.input.dto.request.place.PlaceRecommendRequest;
import trible.histour.application.port.input.dto.response.place.PlacesResponse;
import trible.histour.input.http.controller.docs.PlaceApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/places")
public class PlaceApi implements PlaceApiDocs {
	private final PlaceUseCase placeUseCase;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@Override
	public SuccessResponse<PlacesResponse> getPlaces(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = placeUseCase.getPlaces(memberId);
		return SuccessResponse.of(response);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/recommend")
	@Override
	public SuccessResponse<?> recommendPlace(Principal principal, @RequestBody PlaceRecommendRequest request) {
		val memberId = Long.parseLong(principal.getName());
		placeUseCase.recommendPlace(memberId, request.content());
		return SuccessResponse.ofEmpty();
	}
}
