package trible.histour.application.domain.character;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Character {
	Long id;
	@NotNull CharacterType characterType;
}
