package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.request.auth.SignInRequest;
import trible.histour.application.port.input.dto.response.auth.SignInResponse;

public interface AuthUseCase {
	SignInResponse signIn(String socialAccessToken, SignInRequest request);

	SignInResponse reissueAccessToken(long memberId);

	void signOut(long memberId);

	void withdraw(long memberId);
}
