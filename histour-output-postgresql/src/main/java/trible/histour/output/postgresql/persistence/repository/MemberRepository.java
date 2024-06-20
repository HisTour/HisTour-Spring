package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
