package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.Member;
import trible.histour.application.domain.member.SocialType;

@Entity
@NoArgsConstructor
@Table(
	name = "member",
	schema = "histour",
	uniqueConstraints = @UniqueConstraint(columnNames = {"social_id", "social_type"}))
public class MemberEntity extends BaseEntity {
	@Column(nullable = false, updatable = false)
	@Enumerated(value = EnumType.STRING)
	private SocialType socialType;
	@Column(nullable = false, updatable = false)
	private String socialId;
	@Column(nullable = false)
	private String username;
	private String refreshToken;
	private Long characterId;

	public MemberEntity(SocialInfo social, String username) {
		this.socialType = social.type();
		this.socialId = social.id();
		this.username = username;
	}

	public Member toDomain() {
		return Member.builder()
			.id(this.getId())
			.socialType(socialType)
			.socialId(socialId)
			.username(username)
			.refreshToken(refreshToken)
			.characterId(characterId)
			.build();
	}

	public void update(Member member) {
		this.username = member.getUsername();
		this.refreshToken = member.getRefreshToken();
		this.characterId = member.getCharacterId();
	}
}
