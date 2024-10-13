package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionType;

@Entity
@Table(name = "mission", schema = "histour")
@NoArgsConstructor
public class MissionEntity extends BaseEntity {
	@Column(nullable = false)
	private long placeId;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MissionType type;
	@Column(nullable = false)
	private String name;

	public MissionEntity(Mission mission) {
		this.placeId = mission.getPlaceId();
		this.type = mission.getType();
		this.name = mission.getName();
	}

	public Mission toDomain() {
		return Mission.builder()
			.id(getId())
			.placeId(placeId)
			.type(type)
			.name(name)
			.updatedAt(getUpdatedAt())
			.build();
	}
}
