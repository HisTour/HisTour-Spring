package trible.histour.input.http.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AuthUseCase;
import trible.histour.application.port.input.dto.request.auth.SignInRequest;
import trible.histour.application.port.input.dto.response.auth.SignInResponse;
import trible.histour.input.http.controller.docs.AuthApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi implements AuthApiDocs {
	private final AuthUseCase authUseCase;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@Override
	public SuccessResponse<SignInResponse> signIn(
		@RequestHeader("Authorization") String socialAccessToken,
		@RequestBody SignInRequest request
	) {
		val response = authUseCase.signIn(socialAccessToken, request);
		return SuccessResponse.of(response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/token")
	@Override
	public SuccessResponse<SignInResponse> reissueAccessToken(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = authUseCase.reissueAccessToken(memberId);
		return SuccessResponse.of(response);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping
	@Override
	public SuccessResponse<?> logout(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authUseCase.signOut(memberId);
		return SuccessResponse.ofEmpty();
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	@Override
	public SuccessResponse<?> withdraw(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authUseCase.withdraw(memberId);
		return SuccessResponse.ofEmpty();
	}
}
