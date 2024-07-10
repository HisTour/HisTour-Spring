package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MemberMissionEntity;

public interface MemberMissionRepository extends JpaRepository<MemberMissionEntity, Long> {
}
