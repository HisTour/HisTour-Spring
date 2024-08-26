package trible.histour.output.postgresql.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
	List<QuizEntity> findAllByMissionIdOrderBySequenceAsc(long missionId);

	List<QuizEntity> findAllByMissionIdIn(List<Long> missionIds);
}
