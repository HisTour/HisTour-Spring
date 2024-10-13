package trible.histour.application.port.output.web;

import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.SocialType;

public interface OauthPort {
	SocialInfo login(SocialType type, String accessToken);
}
