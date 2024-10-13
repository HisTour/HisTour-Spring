package trible.histour.application.port.input.dto.response.character;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import trible.histour.application.domain.character.Character;

public record CharactersResponse(
	@Schema(description = "캐릭터 정보 목록")
	List<CharacterResponse> characters
) {
	public static CharactersResponse from(List<Character> characters) {
		return new CharactersResponse(
			characters.stream().map(CharacterResponse::of).toList()
		);
	}
}
