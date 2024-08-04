package trible.histour.application.domain.character;

import jakarta.validation.constraints.NotNull;

public record Character(
	long id,
	@NotNull String name,
	@NotNull String description,
	@NotNull String imageUrl
) {
}
