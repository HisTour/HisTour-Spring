package trible.histour.application.domain.character;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CharacterImageInfo(
	@NotNull
	String normalImageUrl,
	@NotNull
	String faceImageUrl
) {
}
