package trible.histour.application.port.input.dto.response.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.character.CharacterColorInfo;

@Builder(access = AccessLevel.PRIVATE)
public record CharacterColorResponse(
	@Schema(description = "멘트 색", example = "#fff")
	String comment,
	@Schema(description = "배경 시작 색", example = "#fff")
	String backgroundStart,
	@Schema(description = "배경 끝 색", example = "#fff")
	String backgroundEnd
) {

	public static CharacterColorResponse of(CharacterColorInfo colorInfo) {
		return CharacterColorResponse.builder()
			.comment(colorInfo.commentColor())
			.backgroundStart(colorInfo.backgroundStartColor())
			.backgroundEnd(colorInfo.backgroundEndColor())
			.build();
	}
}
