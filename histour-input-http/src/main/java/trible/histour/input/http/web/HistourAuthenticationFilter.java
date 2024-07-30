package trible.histour.input.http.web;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.domain.auth.HistourAuthentication;
import trible.histour.application.domain.auth.TokenManager;

@Component
@RequiredArgsConstructor
public class HistourAuthenticationFilter extends OncePerRequestFilter {
	private final TokenManager tokenManager;

	private static final String TOKEN_HEADER_NAME = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		val token = getJwt(request);
		if (isValidToken(token)) {
			val memberUid = tokenManager.getMemberUid(token);
			val authentication = HistourAuthentication.create(memberUid);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		val bearerToken = request.getHeader(TOKEN_HEADER_NAME);
		return (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX))
				? bearerToken.substring(TOKEN_PREFIX.length())
				: null;
	}

	private boolean isValidToken(String token) {
		return StringUtils.hasText(token) && tokenManager.validateToken(token);
	}
}
