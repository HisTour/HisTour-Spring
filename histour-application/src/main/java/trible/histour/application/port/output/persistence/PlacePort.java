package trible.histour.application.port.output.persistence;

import trible.histour.application.domain.place.Place;

public interface PlacePort {
	Place findById(long placeId);
}
