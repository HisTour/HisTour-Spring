package trible.histour.application.port.output.web.dto.response;

import java.util.List;

public record DataHolidayResponse(
	Header header,
	Body body
) {

	public record Header(
		String resultCode,
		String resultMsg
	) {
	}

	public record Body(
		List<Item> items,
		int numOfRows,
		int pageNo,
		int totalCount
	) {
	}

	public record Item(
		String dateKind,
		String dateName,
		String isHoliday,
		String locdate,
		int seq
	) {
	}
}
