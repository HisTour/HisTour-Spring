package trible.histour.application;

import javax.crypto.KeyGenerator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.val;
import trible.histour.application.domain.auth.SecretKeyFactory;
import trible.histour.application.domain.auth.TokenManager;

@ExtendWith(MockitoExtension.class)
class TokenManagerTest {
	@InjectMocks
	TokenManager tokenManager;

	@Mock
	SecretKeyFactory secretKeyFactory;

	@BeforeEach
	void setUp() throws Exception {
		val keyGenerator = KeyGenerator.getInstance("HmacSHA256");
		keyGenerator.init(256);
		val secretKey = keyGenerator.generateKey();

		Mockito.when(secretKeyFactory.create()).thenReturn(secretKey);
	}

	@Test
	@DisplayName("토큰 정상 발급 및 유효성 검증")
	void generateAndValidateToken() {
		// given
		val memberId = 0L;
		val expiredTime = 60 * 60 * 1000 * 2 * 12 * 1000000L;

		// when
		val token = tokenManager.generateToken(memberId, expiredTime);
		val validatedMemberId = tokenManager.getMemberId(token);

		// then
		Assertions.assertThat(validatedMemberId).isEqualTo(memberId);
	}
}
