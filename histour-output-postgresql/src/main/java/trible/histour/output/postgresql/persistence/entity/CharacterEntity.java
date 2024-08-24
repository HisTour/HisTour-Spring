package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.character.Character;

@Entity
@Table(name = "character", schema = "histour")
@NoArgsConstructor
public class CharacterEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	public Character toDomain() {
		return Character.builder()
			.id(this.getId())
			.name(this.name)
			.description(this.description)
			.build();
	}
}
