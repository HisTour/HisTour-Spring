package trible.histour.application.domain.membermission;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberMission {
	Long id;
	@NotNull
	UUID memberUid;
	long placeId;
	@NotNull
	MissionState state;
}
