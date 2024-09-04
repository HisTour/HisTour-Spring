package trible.histour.output.web.data.adapter;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.output.web.DataPort;
import trible.histour.application.port.output.web.dto.response.DataHolidayResponse;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.web.data.config.DataProperties;

@Component
@RequiredArgsConstructor
public class DataAdapter implements DataPort {
	private final DataProperties properties;

	@Override
	public DataHolidayResponse getHoliday() {
		try {
			val now = LocalDate.now();
			val uri = UriComponentsBuilder
				.fromUriString(properties.holiday().api())
				.queryParam("serviceKey", properties.holiday().secret())
				.queryParam("solYear", now.getYear())
				.queryParam("solMonth", String.format("%02d", now.getMonthValue()))
				.build()
				.toString();

			val responseBytes = RestClient.create()
				.get()
				.uri(new URI(uri))
				.retrieve()
				.body(byte[].class);

			assert responseBytes != null;
			val response = new String(responseBytes, StandardCharsets.UTF_8);
			return new XmlMapper().readValue(response, DataHolidayResponse.class);
		} catch (URISyntaxException | JsonProcessingException exception) {
			throw new RuntimeException(exception);
		} catch (RuntimeException exception) {
			throw new HistourException(ExceptionCode.INTERNAL_SERVER_ERROR, "[OpenAPI 예외] " + exception.getMessage());
		}
	}
}
