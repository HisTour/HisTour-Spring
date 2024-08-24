package trible.histour.application.port.output.persistence;

import trible.histour.application.domain.mission.Mission;

public interface MissionPort {
	Mission findById(long missionId);
}
