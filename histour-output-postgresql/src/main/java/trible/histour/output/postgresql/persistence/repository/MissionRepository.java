package trible.histour.output.postgresql.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MissionEntity;

public interface MissionRepository extends JpaRepository<MissionEntity, Long> {
	List<MissionEntity> findAllByPlaceId(long placeId);

	List<MissionEntity> findAllByIdIn(List<Long> ids);
}
