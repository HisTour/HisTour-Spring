package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.output.postgresql.persistence.entity.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
