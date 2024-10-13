package trible.histour.output.postgresql.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.quiz.MemberQuiz;
import trible.histour.application.domain.quiz.Quiz;
import trible.histour.application.port.output.persistence.QuizPort;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.postgresql.persistence.entity.QuizEntity;
import trible.histour.output.postgresql.persistence.repository.QuizRepository;

@Component
@RequiredArgsConstructor
public class QuizAdapter implements QuizPort {
	private final QuizRepository quizRepository;

	@Override
	public List<Quiz> findAllByMissionId(long missionId) {
		return quizRepository.findAllByMissionIdOrderBySequenceAsc(missionId)
			.stream().map(QuizEntity::toDomain).toList();
	}

	@Override
	public List<Quiz> findAllByMissionIds(List<Long> missionIds) {
		return quizRepository.findAllByMissionIdIn(missionIds)
			.stream().map(QuizEntity::toDomain).toList();
	}

	@Override
	public Quiz findById(long quizId) {
		return find(quizId).toDomain();
	}

	private QuizEntity find(long id) {
		return quizRepository.findById(id)
				.orElseThrow(() -> new HistourException(ExceptionCode.NOT_FOUND, "QuizID: " + id));
	}
}
