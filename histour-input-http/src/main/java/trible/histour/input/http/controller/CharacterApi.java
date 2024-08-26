package trible.histour.input.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import trible.histour.application.port.input.CharacterUseCase;
import trible.histour.application.port.input.dto.response.character.CharactersResponse;
import trible.histour.input.http.controller.docs.CharacterApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/characters")
public class CharacterApi implements CharacterApiDocs {
	private final CharacterUseCase characterUseCase;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<CharactersResponse> getCharacters() {
		return SuccessResponse.of(
			characterUseCase.getCharacters()
		);
	}
}
