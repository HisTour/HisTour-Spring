package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.MissionState;

public interface MemberMissionPort {
	List<MemberMission> findAllByMemberIdAndMissionIds(long memberId, List<Long> missionIds);

	MemberMission save(long memberId, long missionId);

	List<MemberMission> findAllByMemberId(long memberId);

	MemberMission findByMemberIdAndMissionId(long memberId, long missionId);

	void update(MemberMission memberMission);

	List<MemberMission> findAllByMemberIdAndState(long memberId, MissionState missionState);

	boolean isExistByMemberIdAndMissionId(long memberId, long missionId);
}
