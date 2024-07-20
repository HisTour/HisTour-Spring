package trible.histour.application.domain.auth;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.AccessLevel;
import lombok.Builder;

public class HistourAuthentication extends UsernamePasswordAuthenticationToken {

		@Builder(access = AccessLevel.PRIVATE)
		public HistourAuthentication(
						Object principal,
						Object credentials,
						Collection<? extends GrantedAuthority> authorities
		) {
				super(principal, credentials, authorities);
		}

		public static <T> HistourAuthentication create(T principal) {
				return HistourAuthentication.builder()
								.principal(principal)
								.credentials(null)
								.authorities(null)
								.build();
		}
}
