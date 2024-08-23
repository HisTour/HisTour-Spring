package trible.histour.application.domain.quiz;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberQuiz {
	Long id;
	long memberId;
	long quizId;
}
