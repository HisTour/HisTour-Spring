package trible.histour.application.domain.attraction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Attraction {
	Long id;
	@NotNull
	String name;
	@NotNull
	String description;
	@NotNull
	String imageUrl;
}
