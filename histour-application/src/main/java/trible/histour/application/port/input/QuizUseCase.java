package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;

public interface QuizUseCase {
	QuizzesResponse getQuizzes(long missionId);
}
