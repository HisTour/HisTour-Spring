package trible.histour.application.domain.attraction;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AttractionUseCase;
import trible.histour.application.port.input.dto.response.attraction.AttractionResponse;
import trible.histour.application.port.input.dto.response.attraction.AttractionsResponse;
import trible.histour.application.port.output.web.DataPort;
import trible.histour.application.port.output.web.dto.response.DataAttractionResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttractionService implements AttractionUseCase {
	private final DataPort dataPort;
	@Override
	public AttractionsResponse getAttractions() {
		val response = dataPort.getAttraction();

		List<DataAttractionResponse.Response.Body.Items.Item> items = response.response().body().items().item();

		Collections.shuffle(items);
		List<DataAttractionResponse.Response.Body.Items.Item> randomItems = items.stream().limit(4).toList();

		List<AttractionResponse> attractionResponses = randomItems.stream()
				.map(item -> new AttractionResponse(item.title(), item.script(), item.imageUrl()))
						.toList();

		return new AttractionsResponse(attractionResponses);
	}
}
