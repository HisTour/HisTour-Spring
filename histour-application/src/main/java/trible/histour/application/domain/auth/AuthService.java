package trible.histour.application.domain.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AuthUseCase;
import trible.histour.application.port.input.dto.request.auth.SignInRequest;
import trible.histour.application.port.input.dto.response.auth.SignInResponse;
import trible.histour.application.port.output.persistence.MemberPort;
import trible.histour.application.port.output.web.OauthPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService implements AuthUseCase {
	private final MemberPort memberPort;
	private final OauthPort oauthPort;
	private final TokenManager tokenManager;

	private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000 * 2 * 12 * 1000000L;
	private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000 * 24 * 14L;

	@Transactional
	@Override
	public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
		val signedSocial = oauthPort.login(request.type(), socialAccessToken);
		val signedMember = memberPort.signInBySocial(signedSocial);
		signedMember.updateRefreshToken(generateRefreshToken(signedMember.getId()));
		memberPort.update(signedMember);
		return SignInResponse.of(signedMember, generateAccessToken(signedMember.getId()));
	}

	private String generateAccessToken(long memberId) {
		return tokenManager.generateToken(memberId, ACCESS_TOKEN_EXPIRATION_TIME);
	}

	private String generateRefreshToken(long memberId) {
		return tokenManager.generateToken(memberId, REFRESH_TOKEN_EXPIRATION_TIME);
	}
}
