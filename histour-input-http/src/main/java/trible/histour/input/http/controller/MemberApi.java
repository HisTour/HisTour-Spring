package trible.histour.input.http.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.MemberUseCase;
import trible.histour.application.port.input.dto.request.member.MemberProfileUpdateRequest;
import trible.histour.application.port.input.dto.response.member.MemberInfoResponse;
import trible.histour.input.http.controller.docs.MemberApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApi implements MemberApiDocs {
	private final MemberUseCase memberUseCase;

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/profile")
	@Override
	public SuccessResponse<?> updateProfile(Principal principal, @RequestBody MemberProfileUpdateRequest request) {
		val memberId = Long.parseLong(principal.getName());
		memberUseCase.updateProfile(memberId, request);
		return SuccessResponse.ofEmpty();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/info")
	@Override
	public SuccessResponse<MemberInfoResponse> getMemberInfo(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberUseCase.getMemberInfo(memberId);
		return SuccessResponse.of(response);
	}
}
