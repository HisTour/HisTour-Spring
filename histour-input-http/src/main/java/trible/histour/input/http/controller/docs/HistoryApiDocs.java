package trible.histour.input.http.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.response.history.HolidaysResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "HistoryApi", description = "역사 관련 api")
public interface HistoryApiDocs {

	@Operation(
		summary = "국경일(역사 이야기) 조회 api",
		description = "역사 이야기 정보를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success")
		}
	)
	SuccessResponse<HolidaysResponse> getHolidays();
}
