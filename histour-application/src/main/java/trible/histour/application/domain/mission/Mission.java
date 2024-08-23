package trible.histour.application.domain.mission;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Mission {
	Long id;
	long placeId;
	@NotNull
	MissionType type;
	@NotNull
	String name;
}
