package trible.histour.application.port.input.dto.response.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.place.Place;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "장소 조회 정보 응답")
public record PlaceResponse(
	@Schema(description = "여행지 id", example = "1")
	long placeId,
	@Schema(description = "여행지 이름", example = "수원")
	String name,
	@Schema(description = "여행지 설명", example = "수원은 어쩌구 ...더보기")
	String description,
	@Schema(description = "수행한 미션 개수", example = "3")
	int clearedMissionCount,
	@Schema(description = "전체 미션 개수", example = "10")
	int totalMissionCount,
	@Schema(description = "서브미션 고르는 이미지 url", example = "https://test.com")
	String missionsImageUrl
) {

	public static PlaceResponse of(Place place, int clearedMissionCount) {
		return PlaceResponse.builder()
			.placeId(place.getId())
			.name(place.getName())
			.description(place.getDescription())
			.clearedMissionCount(clearedMissionCount)
			.totalMissionCount(place.getRequiredMissionCount())
			.missionsImageUrl(place.getMissionsImageUrl())
			.build();
	}
}
