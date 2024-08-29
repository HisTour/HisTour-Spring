package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.character.Character;

public interface CharacterPort {
	List<Character> findAll();

	Character findById(long characterId);
}
