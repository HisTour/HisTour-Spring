package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "character", schema = "histour")
@NoArgsConstructor
public class CharacterEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	@Column(nullable = false)
	private String imageUrl;
}
