package trible.histour.output.postgresql.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.AttractionEntity;


public interface AttractionRepository extends JpaRepository<AttractionEntity, Long> {
	List<AttractionEntity> findAllByIdIn(List<Long> ids);

	Long countAttraction();
}
