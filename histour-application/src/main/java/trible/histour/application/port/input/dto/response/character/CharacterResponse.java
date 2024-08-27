package trible.histour.application.port.input.dto.response.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.character.Character;

@Builder(access = AccessLevel.PRIVATE)
public record CharacterResponse(
	@Schema(description = "캐릭터 id", example = "1")
	long id,
	@Schema(description = "캐릭터 이름", example = "왕도깨비")
	String name,
	@Schema(description = "캐릭터 설명", example = "예로부터 왕의 옷을 입은 도깨비는 ...더보기")
	String description,
	@Schema(description = "캐릭터 이미지 정보")
	CharacterImageResponse image,
	@Schema(description = "캐릭터 이미지 정보")
	CharacterCommentResponse comment,
	@Schema(description = "캐릭터 색 정보")
	CharacterColorResponse color
) {
	public static CharacterResponse of(Character character) {
		return CharacterResponse.builder()
			.id(character.id())
			.name(character.name())
			.description(character.description())
			.image(CharacterImageResponse.of(character.imageInfo()))
			.comment(CharacterCommentResponse.of(character.commentInfo()))
			.color(CharacterColorResponse.of(character.colorInfo()))
			.build();
	}
}
