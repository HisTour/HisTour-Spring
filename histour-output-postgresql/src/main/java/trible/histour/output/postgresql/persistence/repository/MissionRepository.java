package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MissionEntity;

public interface MissionRepository extends JpaRepository<MissionEntity, Long> {
}
