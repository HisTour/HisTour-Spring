package trible.histour.application.port.input.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.character.Character;

public record CharactersResponse(
	@Schema(description = "캐릭터 정보 목록")
	List<CharacterResponse> characterResponses
) {
	public static CharactersResponse from(List<Character> characters) {
		return new CharactersResponse(
			characters.stream().map(CharacterResponse::from).toList()
		);
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record CharacterResponse(
		@Schema(description = "캐릭터 id", example = "1")
		long id,
		@Schema(description = "캐릭터 이름", example = "왕도깨비")
		String name,
		@Schema(description = "캐릭터 설명", example = "예로부터 왕의 옷을 입은 도깨비는 ...더보기")
		String description
	) {
		private static CharacterResponse from(Character character) {
			return CharacterResponse.builder()
				.id(character.id())
				.name(character.name())
				.description(character.description())
				.build();
		}
	}
}
