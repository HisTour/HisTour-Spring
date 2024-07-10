package trible.histour.application.domain.member;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
		UUID memberId;
		@NotNull SocialType socialType;
		@NotNull String socialId;
		String profileImageUrl;
		@NotNull String username;
		String refreshToken;

		Long characterId;
}
