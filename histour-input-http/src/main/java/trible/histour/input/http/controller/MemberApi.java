package trible.histour.input.http.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.MemberUseCase;
import trible.histour.application.port.input.dto.request.member.CharacterUpdateRequest;
import trible.histour.input.http.controller.docs.MemberApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApi implements MemberApiDocs {
	private final MemberUseCase memberUseCase;

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/character")
	@Override
	public SuccessResponse<?> updateCharacters(Principal principal, @RequestBody CharacterUpdateRequest request) {
		val memberId = Long.parseLong(principal.getName());
		val characterId = request.characterId();
		memberUseCase.updateCharacter(memberId, characterId);
		return SuccessResponse.ofEmpty();
	}
}
