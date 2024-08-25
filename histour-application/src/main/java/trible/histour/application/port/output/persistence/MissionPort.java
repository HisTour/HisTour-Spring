package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.mission.Mission;

public interface MissionPort {
	Mission findById(long missionId);

	List<Mission> findAllByPlaceId(long placeId);
}
