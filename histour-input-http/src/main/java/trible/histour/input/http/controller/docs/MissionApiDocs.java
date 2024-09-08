package trible.histour.input.http.controller.docs;

import java.security.Principal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.response.mission.MissionsResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "MissionApi", description = "미션 관련 api")
public interface MissionApiDocs {

	@Operation(
		summary = "미션 목록 조회 api",
		description = "여행지에 해당하는 미션 정보 목록을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success")
		}
	)
	SuccessResponse<MissionsResponse> getMissions(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			description = "여행지 id",
			required = true,
			in = ParameterIn.PATH
		) long placeId
	);

	@Operation(
		summary = "미션 해금 api",
		description = "서브미션을 COMPLETE 상태로 변경합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success")
		}
	)
	SuccessResponse<?> completeMemberMission(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			description = "미션 id",
			required = true,
			in = ParameterIn.PATH
		) long missionId
	);
}
