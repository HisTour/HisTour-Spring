package trible.histour.histouroutputpostgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.histouroutputpostgresql.persistence.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
