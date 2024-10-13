package trible.histour.input.http.controller.docs;

import java.security.Principal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.request.mission.UpdatedMissionsRequest;
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
		description = "완료한 서브미션을 COMPLETE, 다음 선택된 서브미션을 PROGRESS 상태로 업데이트합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success")
		}
	)
	SuccessResponse<?> unlockMemberMission(
		@Parameter(hidden = true) Principal principal,
		@RequestBody(
			description = "미션 해금 Request Body",
			required = true,
			content = @Content(schema = @Schema(implementation = UpdatedMissionsRequest.class))
		) UpdatedMissionsRequest request
	);
}
