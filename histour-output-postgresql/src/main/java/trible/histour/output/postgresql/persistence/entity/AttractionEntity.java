package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import trible.histour.application.domain.attraction.Attraction;

@Entity
@Table(name = "attraction", schema = "histour")
public class AttractionEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(nullable = false)
	private String imageUrl;

	public Attraction toDomain() {
		return Attraction.builder()
			.id(this.getId())
			.name(this.name)
			.description(this.description)
			.imageUrl(this.imageUrl)
			.build();
	}
}
