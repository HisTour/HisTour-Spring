package trible.histour.output.postgresql.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trible.histour.application.domain.story.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
