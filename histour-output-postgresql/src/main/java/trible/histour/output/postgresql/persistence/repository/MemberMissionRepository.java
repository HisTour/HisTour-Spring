package trible.histour.output.postgresql.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.application.domain.mission.MissionState;
import trible.histour.output.postgresql.persistence.entity.MemberMissionEntity;

public interface MemberMissionRepository extends JpaRepository<MemberMissionEntity, Long> {
	List<MemberMissionEntity> findByMemberIdAndMissionIdIn(long memberId, List<Long> missionIds);

	List<MemberMissionEntity> findAllByMemberId(long memberId);

	void deleteByMemberId(long memberId);

	Optional<MemberMissionEntity> findByMemberIdAndMissionId(long memberId, long missionId);

	List<MemberMissionEntity> findAllByMemberIdAndState(long memberId, MissionState missionState);
}
