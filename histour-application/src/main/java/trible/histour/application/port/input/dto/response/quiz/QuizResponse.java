package trible.histour.application.port.input.dto.response.quiz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import trible.histour.application.domain.quiz.Quiz;
import trible.histour.application.domain.quiz.QuizType;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "퀴즈 정보 응답")
public record QuizResponse(
	@Schema(description = "퀴즈 id", example = "1")
	long id,
	@Schema(description = "퀴즈 타입", example = "KEYWORD")
	QuizType type,
	@Schema(description = "퀴즈 힌트", example = "화성행궁의 중심")
	String hint,
	@Schema(description = "퀴즈 답", example = "봉수당")
	String answer,
	@Schema(description = "퀴즈 이미지 url", example = "https://www.test")
	String imageUrl,
	@Schema(description = "퀴즈 순서", example = "1")
	int sequence
) {

	public static QuizResponse of(Quiz quiz) {
		return QuizResponse.builder()
			.id(quiz.getId())
			.type(quiz.getType())
			.hint(quiz.getHint())
			.answer(quiz.getAnswer())
			.imageUrl(quiz.getImageUrl())
			.sequence(quiz.getSequence())
			.build();
	}
}
