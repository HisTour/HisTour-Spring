package trible.histour.output.postgresql.adapter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.port.output.persistence.MissionPort;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.postgresql.persistence.entity.MissionEntity;
import trible.histour.output.postgresql.persistence.repository.MissionRepository;

@Component
@RequiredArgsConstructor
public class MissionAdapter implements MissionPort {
	private final MissionRepository missionRepository;

	@Override
	public Mission findById(long missionId) {
		return find(missionId).toDomain();
	}

	private MissionEntity find(long id) {
		return missionRepository.findById(id)
			.orElseThrow(() -> new HistourException(ExceptionCode.NOT_FOUND, "Mission ID: " + id));
	}
}
