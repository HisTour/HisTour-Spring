package trible.histour.application.domain.auth;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenManager {
	private final SecretKeyFactory secretKeyFactory;

	public String generateToken(long memberId, Long tokenExpirationTime) {
		val authentication = HistourAuthentication.create(memberId);
		val now = new Date();
		val claims = Jwts.claims()
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + tokenExpirationTime));

		claims.put("memberId", authentication.getPrincipal());

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.signWith(secretKeyFactory.create())
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			getBody(token);
		} catch (RuntimeException exception) {
			log.error(exception.getMessage());
			return false;
		}
		return true;
	}

	public long getMemberId(String token) {
		val claims = getBody(token);
		return Long.parseLong(claims.get("memberId").toString());
	}

	private Claims getBody(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKeyFactory.create())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}
}
