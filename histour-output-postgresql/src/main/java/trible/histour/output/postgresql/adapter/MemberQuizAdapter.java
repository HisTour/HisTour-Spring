package trible.histour.output.postgresql.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.quiz.MemberQuiz;
import trible.histour.application.port.output.persistence.MemberQuizPort;
import trible.histour.output.postgresql.persistence.entity.MemberQuizEntity;
import trible.histour.output.postgresql.persistence.repository.MemberQuizRepository;

@Component
@RequiredArgsConstructor
public class MemberQuizAdapter implements MemberQuizPort {
	private final MemberQuizRepository memberQuizRepository;

	@Override
	public List<MemberQuiz> findAllByMemberIdAndQuizIds(long memberId, List<Long> quizIds) {
		return memberQuizRepository.findAllByMemberIdAndQuizIdIn(memberId, quizIds).stream()
			.map(MemberQuizEntity::toDomain).toList();
	}
}
