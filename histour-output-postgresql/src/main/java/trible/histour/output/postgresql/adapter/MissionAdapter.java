package trible.histour.output.postgresql.adapter;

import java.util.List;

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

	@Override
	public List<Mission> findAllByPlaceId(long placeId) {
		return missionRepository.findAllByPlaceId(placeId)
			.stream().map(MissionEntity::toDomain).toList();
	}

	@Override
	public List<Mission> findAllByMissionIds(List<Long> missionIds) {
		return missionRepository.findAllByIdIn(missionIds).stream().map(MissionEntity::toDomain).toList();
	}

	private MissionEntity find(long id) {
		return missionRepository.findById(id)
			.orElseThrow(() -> new HistourException(ExceptionCode.NOT_FOUND, "Mission ID: " + id));
	}
}
