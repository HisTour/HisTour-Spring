package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.place.Place;
import trible.histour.application.domain.place.RegionType;

@Entity
@Table(name = "place", schema = "histour")
@NoArgsConstructor
public class PlaceEntity extends BaseEntity {
		@Column(nullable = false)
		private String name;
		@Column(nullable = false)
		private String description;
		@Column(nullable = false)
		@Enumerated(EnumType.STRING)
		private RegionType regionType;

		public PlaceEntity(Place place) {
				this.name = place.getName();
				this.description = place.getDescription();
				this.regionType = place.getRegionType();
		}

		public Place toDomain() {
				return Place.builder()
								.id(getId())
								.name(name)
								.description(description)
								.regionType(regionType)
								.build();
		}
}
