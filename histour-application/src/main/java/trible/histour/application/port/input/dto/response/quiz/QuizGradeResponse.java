package trible.histour.application.port.input.dto.response.quiz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "퀴즈 채점 정보 응답")
public record QuizGradeResponse(
		@Schema(description = "퀴즈 정답 유뮤", example = "true")
		boolean isCorrect,
		@Schema(description = "수행한 서브 미션 개수", example = "3")
		Integer clearedMissionCount,
		@Schema(description = "필요한 서브 미션 개수", example = "5")
		Integer requireMissionCount
) {
	public static QuizGradeResponse of(boolean isCorrect, Integer clearedMissionCount, Integer requireMissionCount) {
		return QuizGradeResponse.builder()
				.isCorrect(isCorrect)
				.clearedMissionCount(clearedMissionCount)
				.requireMissionCount(requireMissionCount)
				.build();
	}
}
