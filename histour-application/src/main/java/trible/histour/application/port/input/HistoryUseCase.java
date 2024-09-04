package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.response.history.HolidaysResponse;

public interface HistoryUseCase {
	HolidaysResponse getHoliday();
}
