package trible.histour.application.domain.place;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.port.input.PlaceUseCase;
import trible.histour.application.port.input.dto.response.place.PlacesResponse;
import trible.histour.application.port.output.persistence.MemberMissionPort;
import trible.histour.application.port.output.persistence.MissionPort;
import trible.histour.application.port.output.persistence.PlacePort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService implements PlaceUseCase {
	private final PlacePort placePort;
	private final MissionPort missionPort;
	private final MemberMissionPort memberMissionPort;

	@Override
	public PlacesResponse getPlaces(long memberId) {
		val placeById = placePort.findAll().stream()
			.collect(Collectors.toMap(Place::getId, place -> place));

		val memberMissions = memberMissionPort.findAllByMemberId(memberId);
		val missionIds = memberMissions.stream().map(MemberMission::getMissionId).toList();
		val missionsByPlaceId = missionPort.findAllByMissionIds(missionIds).stream()
			.collect(Collectors.groupingBy(Mission::getPlaceId));

		return PlacesResponse.of(placeById, missionsByPlaceId);
	}
}
