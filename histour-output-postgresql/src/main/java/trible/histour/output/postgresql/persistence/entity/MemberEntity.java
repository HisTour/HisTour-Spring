package trible.histour.output.postgresql.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.member.Member;
import trible.histour.application.domain.member.SocialType;

@Entity
@Table(name = "member", schema = "histour")
@NoArgsConstructor
public class MemberEntity extends BaseEntity {
		@Column(nullable = false, unique = true)
		private UUID memberUid;
		@Column(nullable = false)
		@Enumerated(value = EnumType.STRING)
		private SocialType socialType;
		@Column(nullable = false)
		private String socialId;
		private String profileImageUrl;
		@Column(nullable = false)
		private String username;
		private String refreshToken;

		private Long characterId;

		public MemberEntity(Member member) {
				this.memberUid = member.getMemberUid();
				this.socialType = member.getSocialType();
				this.socialId = member.getSocialId();
				this.profileImageUrl = member.getProfileImageUrl();
				this.username = member.getUsername();
				this.refreshToken = member.getRefreshToken();
				this.characterId = member.getCharacterId();
		}

		public Member toDomain() {
				return Member.builder()
								.memberUid(memberUid)
								.socialType(socialType)
								.socialId(socialId)
								.profileImageUrl(profileImageUrl)
								.username(username)
								.refreshToken(refreshToken)
								.characterId(characterId)
								.build();
		}
}
