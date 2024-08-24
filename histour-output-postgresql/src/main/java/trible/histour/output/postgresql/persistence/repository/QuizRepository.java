package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
}
