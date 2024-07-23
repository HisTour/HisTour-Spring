package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.membermission.MemberMission;
import trible.histour.application.domain.membermission.MissionState;

@Entity
@Table(name = "member_mission", schema = "histour")
@NoArgsConstructor
public class MemberMissionEntity extends BaseEntity {
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MissionState missionState;

	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long missionId;
	@Column(nullable = false)
	private long placeId;

	public MemberMissionEntity(MemberMission memberMission) {
		this.missionState = memberMission.getMissionState();
		this.memberId = memberMission.getMemberId();
		this.missionId = memberMission.getMissionId();
		this.placeId = memberMission.getPlaceId();
	}
	public MemberMission toDomain() {
		return MemberMission.builder()
				.id(getId())
				.memberId(memberId)
				.missionId(missionId)
				.placeId(placeId)
				.build();
	}
}
