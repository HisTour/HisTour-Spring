package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.MissionState;

@Entity
@Table(name = "member_mission", schema = "histour")
@NoArgsConstructor
public class MemberMissionEntity extends BaseEntity {
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long missionId;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MissionState state;

	public MemberMissionEntity(MemberMission memberMission) {
		this.memberId = memberMission.getMemberId();
		this.missionId = memberMission.getMissionId();
		this.state = memberMission.getState();
	}

	public MemberMission toDomain() {
		return MemberMission.builder()
			.id(getId())
			.memberId(memberId)
			.missionId(missionId)
			.state(state)
			.createdAt(getCreatedAt())
			.build();
	}
}
