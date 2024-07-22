package trible.histour.application.port.output.persistence;

import trible.histour.application.domain.member.Member;

public interface MemberPort {
	void save(Member member);

	Member find(long id);

	void update(Member member);

	void delete(Member member);
}
