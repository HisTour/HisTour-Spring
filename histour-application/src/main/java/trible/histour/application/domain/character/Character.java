package trible.histour.application.domain.character;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record Character(
	long id,
	@NotNull
	String name,
	@NotNull
	String description,
	@NotNull
	CharacterImageInfo imageInfo,
	@NotNull
	CharacterCommentInfo commentInfo,
	@NotNull
	CharacterColorInfo colorInfo
) {
}
