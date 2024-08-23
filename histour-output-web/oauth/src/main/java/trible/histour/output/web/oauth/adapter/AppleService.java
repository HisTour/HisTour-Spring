package trible.histour.output.web.oauth.adapter;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.SocialType;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.web.oauth.adapter.response.apple.AppleKey;
import trible.histour.output.web.oauth.adapter.response.apple.AppleKeysResponse;
import trible.histour.output.web.oauth.adapter.response.apple.DecodedAppleKey;

@Component
@RequiredArgsConstructor
public class AppleService {
	private final ObjectMapper objectMapper;

	@Value("${oauth.url.apple}")
	private String appleUrl;

	public SocialInfo login(String accessToken) {
		val data = getAppleData(accessToken);
		return SocialInfo.builder()
			.id(data.getSubject())
			.type(SocialType.KAKAO)
			.profileImageUrl("apple image url 임시")
			.username("apple username 임시")
			.build();
	}

	private Claims getAppleData(String accessToken) {
		val publicAppleKeys = getApplePublicKeys();
		val decodedAppleKey = decodeAppleAccessToken(accessToken);
		val matchedAppleKey = matchDecodedKeyWithApplePublicKeys(decodedAppleKey, publicAppleKeys);
		return Jwts.parserBuilder()
			.setSigningKey(generatePublicKey(matchedAppleKey))
			.build()
			.parseClaimsJws(accessToken.substring(7))
			.getBody();
	}

	private AppleKeysResponse getApplePublicKeys() {
		return RestClient.create()
			.get()
			.uri(appleUrl)
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, (appleRequest, appleResponse) -> {
				throw new HistourException(ExceptionCode.BAD_REQUEST);
			})
			.body(AppleKeysResponse.class);
	}

	private AppleKey matchDecodedKeyWithApplePublicKeys(
		DecodedAppleKey decodedAppleKey,
		AppleKeysResponse appleKeyList
	) {
		return appleKeyList.keys().stream()
			.filter(appleKey -> checkEqualAppleKey(decodedAppleKey, appleKey))
			.findFirst()
			.orElseThrow(() -> new HistourException(ExceptionCode.SERVICE_AVAILABLE));
	}

	private boolean checkEqualAppleKey(final DecodedAppleKey decodedAppleKey, final AppleKey appleKey) {
		return Objects.equals(decodedAppleKey.alg(), appleKey.alg())
			&& Objects.equals(decodedAppleKey.kid(), appleKey.kid());
	}

	private DecodedAppleKey decodeAppleAccessToken(final String accessToken) {
		try {
			val encoded = accessToken.split("\\.");
			val decoded = new String(Base64.getDecoder().decode(encoded[0].substring(7)));
			val decodedMap = objectMapper.readValue(decoded, Map.class);
			return DecodedAppleKey.builder()
				.kid(decodedMap.get("kid").toString())
				.alg(decodedMap.get("alg").toString())
				.build();
		} catch (JsonProcessingException exception) {
			throw new HistourException(ExceptionCode.SERVICE_AVAILABLE, exception.getMessage());
		}
	}

	private PublicKey generatePublicKey(final AppleKey key) {
		try {
			val eBytes = Base64.getUrlDecoder().decode(key.e());
			val nBytes = Base64.getUrlDecoder().decode(key.n());
			val publicKeySpec = new RSAPublicKeySpec(
				new BigInteger(1, nBytes),
				new BigInteger(1, eBytes));
			val keyFactory = KeyFactory.getInstance(key.kty());
			return keyFactory.generatePublic(publicKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			throw new HistourException(ExceptionCode.SERVICE_AVAILABLE, exception.getMessage());
		}
	}
}
