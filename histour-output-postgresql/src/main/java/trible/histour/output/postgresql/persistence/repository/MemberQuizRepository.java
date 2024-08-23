package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MemberQuizEntity;

public interface MemberQuizRepository extends JpaRepository<MemberQuizEntity, Long> {
}
