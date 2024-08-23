package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.place.Place;

@Entity
@Table(name = "place", schema = "histour")
@NoArgsConstructor
public class PlaceEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;

	public PlaceEntity(Place place) {
		this.name = place.getName();
		this.description = place.getDescription();
	}

	public Place toDomain() {
		return Place.builder()
			.id(getId())
			.name(name)
			.description(description)
			.build();
	}
}
