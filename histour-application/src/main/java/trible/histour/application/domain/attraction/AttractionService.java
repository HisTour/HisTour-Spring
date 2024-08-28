package trible.histour.application.domain.attraction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AttractionUseCase;
import trible.histour.application.port.input.dto.response.attraction.AttractionsResponse;
import trible.histour.application.port.output.persistence.AttractionPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttractionService implements AttractionUseCase {
	private final AttractionPort attractionPort;

	@Override
	public AttractionsResponse getAttractions() {
		val attractions = attractionPort.findRandomAttractions();
		return AttractionsResponse.of(attractions);
	}
}
