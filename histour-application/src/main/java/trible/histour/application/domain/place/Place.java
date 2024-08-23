package trible.histour.application.domain.place;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Place {
	Long id;
	@NotNull
	String name;
	@NotNull
	String description;
	int requiredMissionCount;
}
