package trible.histour.output.postgresql.adapter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import trible.histour.application.port.output.persistence.MemberQuizPort;
import trible.histour.output.postgresql.persistence.repository.MemberQuizRepository;

@Component
@RequiredArgsConstructor
public class MemberQuizAdapter implements MemberQuizPort {
	private final MemberQuizRepository memberQuizRepository;
}
