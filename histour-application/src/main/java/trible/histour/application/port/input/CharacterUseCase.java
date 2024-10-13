package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.response.character.CharactersResponse;

public interface CharacterUseCase {
	CharactersResponse getCharacters();
}
