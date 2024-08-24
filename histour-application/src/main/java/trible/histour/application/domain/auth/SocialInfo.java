package trible.histour.application.domain.auth;

import lombok.Builder;
import trible.histour.application.domain.member.SocialType;

@Builder
public record SocialInfo(
	String id,
	SocialType type,
	String profileImageUrl,
	String username
) {
}
