package trible.histour.output.postgresql.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.attraction.Attraction;
import trible.histour.application.port.output.persistence.AttractionPort;
import trible.histour.output.postgresql.persistence.entity.AttractionEntity;
import trible.histour.output.postgresql.persistence.repository.AttractionRepository;

@Component
@RequiredArgsConstructor
public class AttractionAdapter implements AttractionPort {
	private final AttractionRepository attractionRepository;
	@Override
	public List<Attraction> findAllByAttractionIds(List<Long> ids) {
		return attractionRepository.findAllByIdIn(ids).stream().map(AttractionEntity::toDomain).toList();
	}

	@Override
	public Long countAttraction() {
		return attractionRepository.countAttraction();
	}
}
