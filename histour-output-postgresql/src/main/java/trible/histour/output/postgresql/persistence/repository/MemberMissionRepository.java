package trible.histour.output.postgresql.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.MemberMissionEntity;

public interface MemberMissionRepository extends JpaRepository<MemberMissionEntity, Long> {
	List<MemberMissionEntity> findByMemberIdAndMissionIdIn(long memberId, List<Long> missionIds);

	List<MemberMissionEntity> findAllByMemberId(long memberId);
}
