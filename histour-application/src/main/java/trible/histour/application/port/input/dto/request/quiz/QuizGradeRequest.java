package trible.histour.application.port.input.dto.request.quiz;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "퀴즈 채점 요청 정보")
public record QuizGradeRequest(
		@Schema(description = "퀴즈 id", example = "1")
		long quizId,
		@Schema(description = "사용자 제출 답안", example = "수원")
		String memberAnswer,
		@Schema(description = "마지막 퀴즈 유무", example = "TRUE")
		boolean isLastTask
) {
}
