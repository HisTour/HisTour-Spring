package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.memberstory.MemberStory;

@Entity
@Table(name = "member_story", schema = "histour")
@NoArgsConstructor
public class MemberStoryEntity extends BaseEntity {
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long placeId;
	@Column(nullable = false)
	private String storyType;
	public MemberStoryEntity(MemberStory memberStory) {
		this.memberId = memberStory.getMemberId();
		this.placeId = memberStory.getPlaceId();
		this.storyType = memberStory.getStoryType();
	}
	public MemberStory toDomain() {
		return MemberStory.builder()
				.id(getId())
				.memberId(memberId)
				.placeId(placeId)
				.storyType(storyType)
				.build();
	}
}
