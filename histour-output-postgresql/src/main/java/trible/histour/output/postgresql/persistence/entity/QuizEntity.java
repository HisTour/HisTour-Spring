package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.quiz.Quiz;
import trible.histour.application.domain.quiz.QuizType;

@Entity
@Table(name = "quiz", schema = "histour")
@NoArgsConstructor
public class QuizEntity extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private QuizType type;
	@Column(nullable = false)
	private long missionId;
	@Column(nullable = false)
	private String hint;
	@Column(nullable = false)
	private String answer;
	@Column(nullable = false)
	private String imageUrl;
	@Column(nullable = false)
	private int sequence;

	public QuizEntity(Quiz quiz) {
		this.type = quiz.getType();
		this.missionId = quiz.getMissionId();
		this.hint = quiz.getHint();
		this.answer = quiz.getAnswer();
		this.imageUrl = quiz.getImageUrl();
		this.sequence = quiz.getSequence();
	}

	public Quiz toDomain() {
		return Quiz.builder()
			.id(getId())
			.type(type)
			.missionId(missionId)
			.hint(hint)
			.answer(answer)
			.imageUrl(imageUrl)
			.sequence(sequence)
			.build();
	}
}
