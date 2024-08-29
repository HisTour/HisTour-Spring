package trible.histour.application.port.input.dto.response.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.character.Character;
import trible.histour.application.domain.member.Member;
import trible.histour.application.port.input.dto.response.character.CharacterResponse;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "유저 정보 조회 응답")
public record MemberInfoResponse(
		@Schema(description = "사용자 캐릭터 정보")
		CharacterResponse characterResponse,
		@Schema(description = "사용자 닉네임", example = "히스투어")
		String userName,
		@Schema(description = "사용자 프로필 이미지 url", example = "url")
		String profileImageUrl
) {
	public static MemberInfoResponse of(Character character, Member member) {
		return MemberInfoResponse.builder()
				.characterResponse(CharacterResponse.of(character))
				.userName(member.getUsername())
				.profileImageUrl(member.getProfileImageUrl())
				.build();
	}
}
