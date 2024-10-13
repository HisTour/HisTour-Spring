package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.quiz.MemberQuiz;

public interface MemberQuizPort {
	List<MemberQuiz> findAllByMemberIdAndQuizIds(long memberId, List<Long> quizIds);

	void save(MemberQuiz memberQuiz);

	boolean isExistByMemberIdAndQuizId(long memberId, long quizId);
}
