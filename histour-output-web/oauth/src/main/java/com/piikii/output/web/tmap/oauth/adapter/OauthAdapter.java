package com.piikii.output.web.tmap.oauth.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.SocialType;
import trible.histour.application.port.output.web.OauthPort;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;

@Component
@RequiredArgsConstructor
public class OauthAdapter implements OauthPort {
	private final KakaoService kakaoService;
	private final AppleService appleService;

	@Override
	public SocialInfo login(SocialType type, String accessToken) {
		try {
			return switch (type) {
				case KAKAO -> kakaoService.login(accessToken);
				case APPLE -> appleService.login(accessToken);
				case DEVELOPER -> developerLogin();
			};
		} catch (RuntimeException exception) {
			throw new HistourException(
				ExceptionCode.SERVICE_AVAILABLE,
				"SocialType: " + type + "\n" + exception.getMessage());
		}
	}

	private SocialInfo developerLogin() {
		return SocialInfo.builder()
			.id(UUID.randomUUID().toString())
			.type(SocialType.DEVELOPER)
			.profileImageUrl("")
			.username("test-user")
			.build();
	}
}
