package trible.histour.application.domain.character;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CharacterColorInfo(
	@NotNull
	String commentColor,
	@NotNull
	String backgroundStartColor,
	@NotNull
	String backgroundEndColor
) {
}
