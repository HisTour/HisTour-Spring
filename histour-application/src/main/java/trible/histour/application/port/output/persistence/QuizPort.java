package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.quiz.Quiz;

public interface QuizPort {
	List<Quiz> findAllByMissionId(long missionId);

	List<Quiz> findAllByMissionIds(List<Long> missionIds);

	Quiz findById(long quizId);
}
