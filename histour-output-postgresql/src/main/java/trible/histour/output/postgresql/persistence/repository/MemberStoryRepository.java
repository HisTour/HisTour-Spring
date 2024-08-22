package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MemberStoryEntity;

public interface MemberStoryRepository extends JpaRepository<MemberStoryEntity, Long> {
}
