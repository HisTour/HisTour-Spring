package trible.histour.application.domain.quiz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.QuizUseCase;
import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;
import trible.histour.application.port.output.persistence.MissionPort;
import trible.histour.application.port.output.persistence.QuizPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService implements QuizUseCase {
	private final QuizPort quizPort;
	private final MissionPort missionPort;

	@Override
	public QuizzesResponse getQuizzes(long missionId) {
		val mission = missionPort.findById(missionId);
		val quizzes = quizPort.findAllByMissionId(missionId);
		return QuizzesResponse.of(mission, quizzes);
	}
}
