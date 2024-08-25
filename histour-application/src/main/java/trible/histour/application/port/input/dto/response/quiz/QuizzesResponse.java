package trible.histour.application.port.input.dto.response.quiz;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.mission.Mission;
import trible.histour.application.domain.mission.MissionType;
import trible.histour.application.domain.quiz.Quiz;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "퀴즈 조회 정보 응답")
public record QuizzesResponse(
	@Schema(description = "미션 이름", example = "화성행궁 - 천자의 명에 따라")
	String missionName,
	@Schema(description = "미션 타입", example = "NORMAL")
	MissionType missionType,
	@Schema(description = "퀴즈 정보 목록")
	List<QuizResponse> quizzes
) {

	public static QuizzesResponse of(Mission mission, List<Quiz> quizzes) {
		return QuizzesResponse.builder()
			.missionName(mission.getName())
			.missionType(mission.getType())
			.quizzes(quizzes.stream().map(QuizResponse::of).toList())
			.build();
	}
}
