package trible.histour.application.port.output.persistence;

import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.Member;

public interface MemberPort {
	Member signInBySocial(SocialInfo social);

	void update(Member member);

	Member findById(long memberId);

	void deleteById(long memberId);
}
