package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.request.quiz.QuizGradeRequest;
import trible.histour.application.port.input.dto.response.quiz.QuizGradeResponse;
import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;

public interface QuizUseCase {
	QuizzesResponse getQuizzes(long memberId, long missionId);

	QuizGradeResponse gradeQuiz(long memberId, QuizGradeRequest quizGradeRequest);
}
