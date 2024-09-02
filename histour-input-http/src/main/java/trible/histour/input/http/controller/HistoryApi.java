package trible.histour.input.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.HistoryUseCase;
import trible.histour.application.port.input.dto.response.history.HolidaysResponse;
import trible.histour.input.http.controller.docs.HistoryApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class HistoryApi implements HistoryApiDocs {
	private final HistoryUseCase historyUseCase;

	@GetMapping("/holiday")
	@ResponseStatus(HttpStatus.OK)
	@Override
	public SuccessResponse<HolidaysResponse> getHolidays() {
		val response = historyUseCase.getHoliday();
		return SuccessResponse.of(response);
	}
}
