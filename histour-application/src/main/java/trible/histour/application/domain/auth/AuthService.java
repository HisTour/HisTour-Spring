package trible.histour.application.domain.auth;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${histour.token.expire_time.access}")
	private long accessTokenExpiredTime;
	@Value("${histour.token.expire_time.refresh}")
	private long refreshTokenExpiredTime;

	@Transactional
	@Override
	public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
		val signedSocial = oauthPort.login(request.type(), socialAccessToken);
		val signedMember = memberPort.signInBySocial(signedSocial);
		signedMember.updateRefreshToken(generateRefreshToken(signedMember.getId()));
		memberPort.update(signedMember);
		return SignInResponse.of(signedMember, generateAccessToken(signedMember.getId()));
	}

	@Override
	public SignInResponse reissueAccessToken(long memberId) {
		val member = memberPort.findById(memberId);
		return SignInResponse.of(member, generateAccessToken(member.getId()));
	}

	@Transactional
	@Override
	public void signOut(long memberId) {
		val member = memberPort.findById(memberId);
		member.updateRefreshToken(null);
		memberPort.update(member);
	}

	@Transactional
	@Override
	public void withdraw(long memberId) {
		memberPort.deleteById(memberId);
	}

	private String generateAccessToken(long memberId) {
		return tokenManager.generateToken(memberId, accessTokenExpiredTime);
	}

	private String generateRefreshToken(long memberId) {
		return tokenManager.generateToken(memberId, refreshTokenExpiredTime);
	}
}
