package trible.histour.output.postgresql.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import trible.histour.output.postgresql.persistence.entity.AttractionEntity;


public interface AttractionRepository extends JpaRepository<AttractionEntity, Long> {
	@Query(value = "SELECT * FROM attraction ORDER BY RANDOM() LIMIT", nativeQuery = true)
	List<AttractionEntity> findRandomAttractions();
}