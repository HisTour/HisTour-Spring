package trible.histour.application.domain.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.MemberUseCase;
import trible.histour.application.port.output.persistence.MemberPort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements MemberUseCase {
	private final MemberPort memberPort;

	@Transactional
	@Override
	public void updateCharacter(long memberId, long characterId) {
		val member = memberPort.findById(memberId);
		member.updateCharacter(characterId);
		memberPort.update(member);
	}
}
