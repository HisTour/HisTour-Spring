package trible.histour.application.domain.mission;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Mission {
	Long id;
	@NotNull
	MissionType missionType;
	@NotNull String content;
	long placeId;
	String storyType;
	String missionHint;
	String missionAnswer;
}
