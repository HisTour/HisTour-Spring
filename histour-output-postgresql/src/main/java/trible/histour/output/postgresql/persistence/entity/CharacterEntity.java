package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.character.Character;
import trible.histour.application.domain.character.CharacterColorInfo;
import trible.histour.application.domain.character.CharacterCommentInfo;
import trible.histour.application.domain.character.CharacterImageInfo;

@Entity
@Table(name = "character", schema = "histour")
@NoArgsConstructor
public class CharacterEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	@Column(nullable = false)
	private String normalImageUrl;
	@Column(nullable = false)
	private String welcomeComment;
	@Column(nullable = false)
	private String commentColor;
	@Column(nullable = false)
	private String backgroundStartColor;
	@Column(nullable = false)
	private String backgroundEndColor;

	public Character toDomain() {
		return Character.builder()
			.id(this.getId())
			.name(this.name)
			.description(this.description)
			.imageInfo(toImageInfo())
			.commentInfo(toCommentInfo())
			.colorInfo(toColorInfo())
			.build();
	}

	private CharacterImageInfo toImageInfo() {
		return CharacterImageInfo.builder()
			.normalImageUrl(this.normalImageUrl)
			.build();
	}

	private CharacterColorInfo toColorInfo() {
		return CharacterColorInfo.builder()
			.commentColor(this.commentColor)
			.backgroundStartColor(this.backgroundStartColor)
			.backgroundEndColor(this.backgroundEndColor)
			.build();
	}

	private CharacterCommentInfo toCommentInfo() {
		return CharacterCommentInfo.builder()
			.welcome(this.welcomeComment)
			.build();
	}
}
