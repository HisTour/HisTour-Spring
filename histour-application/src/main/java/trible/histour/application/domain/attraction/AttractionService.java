package trible.histour.application.domain.attraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		val attractionNumber = attractionPort.countAttraction();
		val randomNumber = getRandomNumbersInRange(1L, attractionNumber);
		val attractions = attractionPort.findAllByAttractionIds(randomNumber);
		return AttractionsResponse.of(attractions);
	}

	public List<Long> getRandomNumbersInRange(long min, long max) {
		List<Long> randomNumbers = new ArrayList<>();
		Random random = new Random();

		while (randomNumbers.size() < 4) {
			val randomNumber = min + (long) (random.nextDouble() * (max - min + 1));
			if (!randomNumbers.contains(randomNumber)) {
				randomNumbers.add(randomNumber);
			}
		}
		return randomNumbers;
	}
}
