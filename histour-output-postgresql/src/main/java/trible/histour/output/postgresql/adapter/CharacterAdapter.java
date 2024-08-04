package trible.histour.output.postgresql.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.character.Character;
import trible.histour.application.port.output.persistence.CharacterPort;
import trible.histour.output.postgresql.persistence.entity.CharacterEntity;
import trible.histour.output.postgresql.persistence.repository.CharacterRepository;

@Component
@RequiredArgsConstructor
public class CharacterAdapter implements CharacterPort {
	private final CharacterRepository characterRepository;

	public List<Character> findAll() {
		val characters = characterRepository.findAllByOrderByIdAsc();
		return characters.stream().map(CharacterEntity::toDomain).toList();
	}
}
