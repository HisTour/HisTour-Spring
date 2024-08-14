package trible.histour.application.domain.character;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.CharacterUseCase;
import trible.histour.application.port.input.dto.response.CharactersResponse;
import trible.histour.application.port.output.persistence.CharacterPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterService implements CharacterUseCase {
	private final CharacterPort characterPort;

	public CharactersResponse getCharacters() {
		val characters = characterPort.findAll();
		return CharactersResponse.from(characters);
	}
}
