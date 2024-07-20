package trible.histour.application.domain.auth;

import static java.util.Base64.*;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Component
@RequiredArgsConstructor
public class SecretKeyFactory {
		@Value("${histour.secret.key}")
		private String HISTOUR_SECRET_KEY;

		public SecretKey create() {
				val encodedKey = getEncoder().encodeToString(HISTOUR_SECRET_KEY.getBytes());
				return Keys.hmacShaKeyFor(encodedKey.getBytes());
		}
}
