package trible.histour.output.postgresql.persistence.entity;

import java.util.UUID;

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
	private UUID memberUid;
	@Column(nullable = false)
	private long quizId;

	public MemberQuizEntity(MemberQuiz memberQuiz) {
		this.memberUid = memberQuiz.getMemberUid();
		this.quizId = memberQuiz.getQuizId();
	}

	public MemberQuiz toDomain() {
		return MemberQuiz.builder()
			.id(getId())
			.memberUid(memberUid)
			.quizId(quizId)
			.build();
	}
}
