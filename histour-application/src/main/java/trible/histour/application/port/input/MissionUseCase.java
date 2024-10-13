package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.request.mission.UpdatedMissionsRequest;
import trible.histour.application.port.input.dto.response.mission.MissionsResponse;

public interface MissionUseCase {
	MissionsResponse getMissions(long memberId, long placeId);

	void completeMemberMission(long memberId, UpdatedMissionsRequest request);
}
