package trible.histour.application.domain.history;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.HistoryUseCase;
import trible.histour.application.port.input.dto.response.history.HolidayResponse;
import trible.histour.application.port.input.dto.response.history.HolidaysResponse;
import trible.histour.application.port.output.web.DataPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService implements HistoryUseCase {
	private final DataPort dataPort;

	@Override
	public HolidaysResponse getHoliday() {
		val response = dataPort.getHoliday();
		val holidays = response.body().items().stream()
			.map(it -> new HolidayResponse(it.locdate(), it.dateName()))
			.toList();
		return new HolidaysResponse(holidays);
	}
}
