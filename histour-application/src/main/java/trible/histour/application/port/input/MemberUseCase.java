package trible.histour.application.port.input;

import trible.histour.application.port.input.dto.response.member.MemberInfoResponse;

public interface MemberUseCase {
	void updateCharacter(long memberId, long characterId);

	MemberInfoResponse getMemberInfo(long memberId);
}
