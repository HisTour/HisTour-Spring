package trible.histour.application.domain.mission;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Mission {
		Long id;
		@NotNull
		List<MissionType> missionTypes;
		@NotNull String content;
		long placeId;
}
