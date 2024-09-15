package trible.histour.application.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import trible.histour.application.port.input.dto.request.member.MemberProfileUpdateRequest;

@Builder
@Getter
public class Member {
	Long id;
	@NotNull
	SocialType socialType;
	@NotNull
	String socialId;
	String profileImageUrl;
	@NotNull
	String username;
	String refreshToken;
	Long characterId;

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void updateProfile(MemberProfileUpdateRequest request) {
		this.characterId = request.characterId();
		this.username = request.username();
	}
}
