package trible.histour.application.domain.character;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CharacterCommentInfo(
	@NotNull
	String welcome
) {

}
