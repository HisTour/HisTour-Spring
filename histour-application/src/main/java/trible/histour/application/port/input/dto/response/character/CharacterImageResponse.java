package trible.histour.application.port.input.dto.response.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.character.CharacterImageInfo;

@Builder(access = AccessLevel.PRIVATE)
public record CharacterImageResponse(
	@Schema(description = "캐릭터 기본 이미지 url", example = "https://www.test")
	String normalImageUrl
) {

	public static CharacterImageResponse of(CharacterImageInfo imageInfo) {
		return CharacterImageResponse.builder()
			.normalImageUrl(imageInfo.normalImageUrl())
			.build();
	}
}
