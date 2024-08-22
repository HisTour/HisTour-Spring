package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.membermission.MemberMission;

@Entity
@Table(name = "member_mission", schema = "histour")
@NoArgsConstructor
public class MemberMissionEntity extends BaseEntity {
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long missionId;
	@Column(nullable = false)
	private long placeId;
	@Column(nullable = false)
	private String storyType;
	public MemberMissionEntity(MemberMission memberMission) {
		this.memberId = memberMission.getMemberId();
		this.missionId = memberMission.getMissionId();
		this.placeId = memberMission.getPlaceId();
		this.storyType = memberMission.getStoryType();
	}
	public MemberMission toDomain() {
		return MemberMission.builder()
				.id(getId())
				.memberId(memberId)
				.missionId(missionId)
				.placeId(placeId)
				.storyType(storyType)
				.build();
	}
}
