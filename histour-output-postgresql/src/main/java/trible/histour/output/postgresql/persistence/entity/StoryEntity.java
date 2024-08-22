package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.story.Story;

@Entity
@Table(name = "story", schema = "histour")
@NoArgsConstructor
public class StoryEntity extends BaseEntity {
	@Column(nullable = false)
	private long placeId;
	@Column(nullable = false)
	private String storyType;
	@Column(nullable = false)
	private String storyName;
	public StoryEntity(Story story) {
		this.placeId = story.getPlaceId();
		this.storyType = story.getStoryType();
		this.storyName = story.getStoryName();
	}
	public Story toDomain() {
		return Story.builder()
				.id(getId())
				.placeId(placeId)
				.storyType(storyType)
				.storyName(storyName)
				.build();
	}
}
