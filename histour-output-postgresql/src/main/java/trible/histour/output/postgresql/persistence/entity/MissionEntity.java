package trible.histour.output.postgresql.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionType;
import trible.histour.output.postgresql.persistence.converter.MissionTypeListConverter;

@Entity
@Table(name = "mission", schema = "histour")
@NoArgsConstructor
public class MissionEntity extends BaseEntity {
	@Convert(converter = MissionTypeListConverter.class)
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "TEXT", nullable = false)
	private List<MissionType> missionTypes = new ArrayList<>();
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private long placeId;

	public MissionEntity(Mission mission) {
		this.missionTypes = mission.getMissionTypes();
		this.content = mission.getContent();
		this.placeId = mission.getPlaceId();
	}

	public Mission toDomain() {
		return Mission.builder()
				.id(getId())
				.missionTypes(missionTypes)
				.content(content)
				.placeId(placeId)
				.build();
	}
}
