package trible.histour.application.domain.memberquiz;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberQuiz {
	Long id;
	UUID memberUid;
	long quizId;
}
