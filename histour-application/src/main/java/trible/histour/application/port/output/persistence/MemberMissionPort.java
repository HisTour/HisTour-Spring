package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.mission.MemberMission;

public interface MemberMissionPort {
	List<MemberMission> findAllByMemberIdAndMissionIds(long memberId, List<Long> missionIds);

	MemberMission save(MemberMission memberMission);
}
