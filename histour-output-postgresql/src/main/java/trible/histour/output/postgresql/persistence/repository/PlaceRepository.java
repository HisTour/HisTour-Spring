package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.PlaceEntity;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
}
