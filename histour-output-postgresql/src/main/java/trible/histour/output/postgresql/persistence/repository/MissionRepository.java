package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.application.domain.mission.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
