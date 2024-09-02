package trible.histour.application.port.input.dto.response.history;

import java.util.List;

public record HolidaysResponse(
	List<HolidayResponse> holidays
) {
}
