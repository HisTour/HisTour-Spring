package trible.histour.application.port.input.dto.response.mission;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;
import trible.histour.application.domain.mission.MemberMission;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionState;
import trible.histour.application.domain.mission.MissionType;
import trible.histour.application.domain.quiz.MemberQuiz;
import trible.histour.application.domain.quiz.Quiz;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "미션 조회 정보 응답")
public record MissionResponse(
	@Schema(description = "미션 id", example = "1")
	long id,
	@Schema(description = "미션 상태", example = "PROGRESS")
	MissionState state,
	@Schema(description = "미션 이름", example = "화성행궁 - 천자의 명에 따라")
	String name,
	@Schema(description = "미션 타입", example = "NORMAL")
	MissionType type,
	@Schema(description = "수행한 퀴즈 개수", example = "3")
	int clearedQuizCount,
	@Schema(description = "전체 퀴즈 개수", example = "6")
	int totalQuizCount,
	@Schema(description = "업데이트 일시", example = "2024-09-08 05:25:09.873962")
	LocalDateTime updatedAt
) {

	public static MissionResponse of(
		Mission mission,
		MemberMission memberMission,
		List<MemberQuiz> memberQuizzes,
		List<Quiz> quizzes
	) {
		val state = (memberMission == null) ? MissionState.BEFORE : memberMission.getState();
		val clearedQuizCount = (memberQuizzes == null) ? 0 : memberQuizzes.size();
		val totalQuizCount = (quizzes == null) ? 0 : quizzes.size();

		return MissionResponse.builder()
			.id(mission.getId())
			.state(state)
			.name(mission.getName())
			.type(mission.getType())
			.clearedQuizCount(clearedQuizCount)
			.totalQuizCount(totalQuizCount)
			.updatedAt(mission.getUpdatedAt())
			.build();
	}
}
