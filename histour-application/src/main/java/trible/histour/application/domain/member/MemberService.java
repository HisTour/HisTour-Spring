package trible.histour.application.domain.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import trible.histour.application.port.input.MemberUseCase;
import trible.histour.application.port.output.persistence.MemberPort;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {
	private final MemberPort memberPort;

	@Override
	public void registerProfile() {
		//TODO: implementation
	}
}
