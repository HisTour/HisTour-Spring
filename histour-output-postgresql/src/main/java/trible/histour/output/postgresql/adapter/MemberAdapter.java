package trible.histour.output.postgresql.adapter;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.auth.SocialInfo;
import trible.histour.application.domain.member.Member;
import trible.histour.application.port.output.persistence.MemberPort;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;
import trible.histour.output.postgresql.persistence.entity.MemberEntity;
import trible.histour.output.postgresql.persistence.repository.MemberMissionRepository;
import trible.histour.output.postgresql.persistence.repository.MemberQuizRepository;
import trible.histour.output.postgresql.persistence.repository.MemberRepository;

@Repository
@RequiredArgsConstructor
public class MemberAdapter implements MemberPort {
	private final MemberRepository memberRepository;
	private final MemberMissionRepository memberMissionRepository;
	private final MemberQuizRepository memberQuizRepository;

	@Override
	public Member signInBySocial(SocialInfo social) {
		return memberRepository.findBySocialIdAndSocialType(social.id(), social.type())
			.map(MemberEntity::toDomain)
			.orElseGet(() -> {
				val username = "깨비" + (memberRepository.count() + 1);
				val memberEntity = new MemberEntity(social, username);
				return memberRepository.save(memberEntity).toDomain();
			});
	}

	@Override
	public void update(Member member) {
		val memberEntity = find(member.getId());
		memberEntity.update(member);
	}

	@Override
	public Member findById(long memberId) {
		return find(memberId).toDomain();
	}

	@Override
	public void deleteById(long memberId) {
		memberQuizRepository.deleteByMemberId(memberId);
		memberMissionRepository.deleteByMemberId(memberId);
		memberRepository.deleteById(memberId);
	}

	private MemberEntity find(long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new HistourException(ExceptionCode.NOT_FOUND, "MemberID: " + id));
	}
}
