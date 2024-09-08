package trible.histour.application.domain.mission;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MemberMission {
	Long id;
	long memberId;
	long missionId;
	@NotNull
	MissionState state;
	@NotNull
	LocalDateTime createdAt;

	public MemberMission(long memberId, long missionId) {
		this.memberId = memberId;
		this.missionId = missionId;
		this.state = MissionState.PROGRESS;
	}

	public boolean isCompleted() {
		return this.state == MissionState.COMPLETE;
	}

	public void complete() {
		this.state = MissionState.COMPLETE;
	}
}
