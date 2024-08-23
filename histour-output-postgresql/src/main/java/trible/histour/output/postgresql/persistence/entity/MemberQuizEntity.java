package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.memberquiz.MemberQuiz;

@Entity
@Table(name = "member_quiz", schema = "histour")
@NoArgsConstructor
public class MemberQuizEntity extends BaseEntity {
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long quizId;

	public MemberQuizEntity(MemberQuiz memberQuiz) {
		this.memberId = memberQuiz.getMemberId();
		this.quizId = memberQuiz.getQuizId();
	}

	public MemberQuiz toDomain() {
		return MemberQuiz.builder()
			.id(getId())
			.memberId(memberId)
			.quizId(quizId)
			.build();
	}
}
