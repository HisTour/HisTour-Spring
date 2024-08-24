package trible.histour.output.postgresql.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.application.domain.member.SocialType;
import trible.histour.output.postgresql.persistence.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findBySocialIdAndSocialType(String socialId, SocialType socialType);
}
