package trible.histour.histouroutputpostgresql.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import trible.histour.application.domain.member.Member;
import trible.histour.application.port.output.persistence.MemberPort;
import trible.histour.histouroutputpostgresql.persistence.repository.MemberRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberAdapter implements MemberPort {
	private final MemberRepository memberRepository;

	@Override
	public void save(Member member) {
		//TODO: implementation
	}

	@Override
	public Member find(long id) {
		//TODO: implementation
		return null;
	}

	@Override
	public void update(Member member) {
		//TODO: implementation
	}

	@Override
	public void delete(Member member) {
		//TODO: implementation
	}
}
