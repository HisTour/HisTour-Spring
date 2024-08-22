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
	@Enumerated(EnumType.STRING)
	private MissionType missionType;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private long placeId;
	@Column(nullable = false)
	private String storyType;
	@Column(nullable = false)
	private String missionHint;
	@Column(nullable = false)
	private String missionAnswer;

	public MissionEntity(Mission mission) {
		this.missionType = mission.getMissionType();
		this.content = mission.getContent();
		this.placeId = mission.getPlaceId();
		this.storyType = mission.getStoryType();
		this.missionHint = mission.getMissionHint();
		this.missionAnswer = mission.getMissionAnswer();
	}

	public Mission toDomain() {
		return Mission.builder()
				.id(getId())
				.missionType(missionType)
				.content(content)
				.placeId(placeId)
				.storyType(storyType)
				.missionHint(missionHint)
				.missionAnswer(missionAnswer)
				.build();
	}
}
