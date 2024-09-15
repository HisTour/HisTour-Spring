package trible.histour.application.domain.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MemberQuiz {
	Long id;
	long memberId;
	long quizId;

	public MemberQuiz(long memberId, long quizId) {
		this.memberId = memberId;
		this.quizId = quizId;
	}
}
