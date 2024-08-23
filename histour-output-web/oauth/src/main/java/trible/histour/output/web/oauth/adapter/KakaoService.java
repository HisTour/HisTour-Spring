package trible.histour.output.web.oauth.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.val;
import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.SocialType;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.web.oauth.adapter.response.kakao.KakaoResponse;

@Component
public class KakaoService {
	@Value("${oauth.url.kakao}")
	private String kakaoUrl;

	public SocialInfo login(String accessToken) {
		val data = getKakaoData(accessToken);
		return SocialInfo.builder()
			.id(data.id())
			.type(SocialType.KAKAO)
			.profileImageUrl(data.kakao_account().profile().profile_image_url())
			.username(data.properties().nickname())
			.build();
	}

	private KakaoResponse getKakaoData(String accessToken) {
		val response = RestClient.create()
			.get()
			.uri(kakaoUrl)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError,
				(kakaoRequest, kakaoResponse) -> {
					throw new HistourException(ExceptionCode.BAD_REQUEST);
				})
			.body(KakaoResponse.class);
		assert response != null;
		return response;
	}
}
