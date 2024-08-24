package trible.histour.application.domain.mission;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberMission {
	Long id;
	long memberId;
	long placeId;
	@NotNull
	MissionState state;
}
