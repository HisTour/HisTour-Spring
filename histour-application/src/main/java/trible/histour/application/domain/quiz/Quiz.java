package trible.histour.application.domain.quiz;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Quiz {
	Long id;
	@NotNull
	QuizType type;
	long missionId;
	@NotNull
	String hint;
	@NotNull
	String answer;
	@NotNull
	String imageUrl;
	int sequence;
}
