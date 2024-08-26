package trible.histour.output.postgresql.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.place.Place;
import trible.histour.application.port.output.persistence.PlacePort;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.postgresql.persistence.entity.PlaceEntity;
import trible.histour.output.postgresql.persistence.repository.PlaceRepository;

@Component
@RequiredArgsConstructor
public class PlaceAdapter implements PlacePort {
	private final PlaceRepository placeRepository;

	@Override
	public Place findById(long placeId) {
		return find(placeId).toDomain();
	}

	@Override
	public List<Place> findAll() {
		return placeRepository.findAll().stream().map(PlaceEntity::toDomain).toList();
	}

	private PlaceEntity find(long id) {
		return placeRepository.findById(id)
			.orElseThrow(() -> new HistourException(ExceptionCode.NOT_FOUND, "PlaceID: " + id));
	}
}
