package trible.histour.input.http.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.response.character.CharactersResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "Character", description = "캐릭터 관련 API")
public interface CharacterApiDocs {
	@Operation(
		summary = "캐릭터 목록 조회 api",
		description = "깨비 캐릭터 전체 목록을 조회합니다."
	)
	SuccessResponse<CharactersResponse> getCharacters();
}
