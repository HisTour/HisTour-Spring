package trible.histour.application.port.output.persistence;

import java.util.List;

import trible.histour.application.domain.attraction.Attraction;

public interface AttractionPort {
	List<Attraction> findAllByAttractionIds(List<Long> ids);

	Long countAttraction();
}
