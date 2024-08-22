package trible.histour.application.domain.membermission;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberMission {
	Long id;
	long memberId;
	long placeId;
	String storyType;
	long missionId;
}
