package trible.histour.application.port.input.dto.response.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.character.CharacterCommentInfo;

@Builder(access = AccessLevel.PRIVATE)
public record CharacterCommentResponse(
	@Schema(description = "환영 멘트", example = "환영하노라")
	String welcome
) {

	public static CharacterCommentResponse of(CharacterCommentInfo commentInfo) {
		return CharacterCommentResponse.builder()
			.welcome(commentInfo.welcome())
			.build();
	}
}
