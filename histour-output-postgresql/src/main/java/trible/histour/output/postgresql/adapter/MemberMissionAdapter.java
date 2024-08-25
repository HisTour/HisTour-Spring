package trible.histour.output.postgresql.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.port.output.persistence.MemberMissionPort;
import trible.histour.output.postgresql.persistence.entity.MemberMissionEntity;
import trible.histour.output.postgresql.persistence.repository.MemberMissionRepository;

@Component
@RequiredArgsConstructor
public class MemberMissionAdapter implements MemberMissionPort {
	private final MemberMissionRepository memberMissionRepository;

	@Override
	public List<MemberMission> findAllByMemberIdAndMissionIds(long memberId, List<Long> missionIds) {
		return memberMissionRepository.findByMemberIdAndMissionIdIn(memberId, missionIds)
			.stream().map(MemberMissionEntity::toDomain).toList();
	}

	@Override
	public MemberMission save(MemberMission memberMission) {
		val memberMissionEntity = new MemberMissionEntity(memberMission);
		return memberMissionRepository.save(memberMissionEntity).toDomain();
	}
}
