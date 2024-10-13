package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.request.member.MemberProfileUpdateRequest;
import trible.histour.application.port.input.dto.response.member.MemberInfoResponse;

public interface MemberUseCase {
	void updateProfile(long memberId, MemberProfileUpdateRequest request);

	MemberInfoResponse getMemberInfo(long memberId);
}
