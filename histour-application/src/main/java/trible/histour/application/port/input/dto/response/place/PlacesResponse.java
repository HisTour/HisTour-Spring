package trible.histour.application.port.input.dto.response.place;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.place.Place;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "여행지 목록 조회 정보 응답")
public record PlacesResponse(
	@Schema(description = "여행지 정보 목록")
	List<PlaceResponse> places
) {

	public static PlacesResponse of(
		Map<Long, Place> placeById,
		Map<Long, List<Mission>> missionsByPlaceId
	) {
		return PlacesResponse.builder()
			.places(toPlaces(placeById, missionsByPlaceId))
			.build();
	}

	private static List<PlaceResponse> toPlaces(
		Map<Long, Place> placeById,
		Map<Long, List<Mission>> missionsByPlaceId
	) {
		return placeById.values().stream()
			.map(place -> {
				val clearedMissionCount = missionsByPlaceId.getOrDefault(place.getId(), List.of()).size();
				return PlaceResponse.of(place, clearedMissionCount);
			})
			.toList();
	}
}
