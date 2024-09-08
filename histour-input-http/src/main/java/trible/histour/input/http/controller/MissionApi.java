package trible.histour.input.http.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.MissionUseCase;
import trible.histour.application.port.input.dto.response.mission.MissionsResponse;
import trible.histour.input.http.controller.docs.MissionApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionApi implements MissionApiDocs {
	private final MissionUseCase missionUseCase;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/place/{placeId}")
	@Override
	public SuccessResponse<MissionsResponse> getMissions(Principal principal, @PathVariable long placeId) {
		val memberId = Long.parseLong(principal.getName());
		val response = missionUseCase.getMissions(memberId, placeId);
		return SuccessResponse.of(response);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/{missionId}/member")
	@Override
	public SuccessResponse<?> completeMemberMission(Principal principal, @PathVariable long missionId) {
		val memberId = Long.parseLong(principal.getName());
		missionUseCase.completeMemberMission(memberId, missionId);
		return SuccessResponse.ofEmpty();
	}
}
