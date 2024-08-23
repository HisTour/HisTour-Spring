package trible.histour.application.domain.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final TokenManager tokenManager;

	private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000 * 2 * 12 * 1000000L;
	private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000 * 24 * 14L;

	//TODO: sign-in 로직에 포함
	public String generateAccessToken(long memberId) {
		return tokenManager.generateToken(memberId, ACCESS_TOKEN_EXPIRATION_TIME);
	}

	//TODO: 토큰 재발급 로직에 포함
	public String generateRefreshToken(long memberId) {
		return tokenManager.generateToken(memberId, REFRESH_TOKEN_EXPIRATION_TIME);
	}
}
