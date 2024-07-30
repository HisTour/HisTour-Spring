package trible.histour.input.http.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;
import trible.histour.input.http.web.HistourAuthenticationEntryPoint;
import trible.histour.input.http.web.HistourAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final HistourAuthenticationFilter authenticationFilter;
	private final HistourAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	@Profile({"dev", "local"})
	public SecurityFilterChain filterChainDev(HttpSecurity http) throws Exception {
		permitOpenApiUri(http);
		setHttp(http);
		return http.build();
	}

	@Bean
	@Profile("prod")
	public SecurityFilterChain filterChainProd(HttpSecurity http) throws Exception {
		setHttp(http);
		return http.build();
	}

	private void setHttp(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.formLogin(AbstractHttpConfigurer::disable)
				.sessionManagement(sessionManagement ->
						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(exceptionHandling ->
						exceptionHandling.authenticationEntryPoint(authenticationEntryPoint))
				.authorizeHttpRequests(authorizeHttpRequests ->
						authorizeHttpRequests
								.requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
								.anyRequest().authenticated())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	private void permitOpenApiUri(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/docs/**")).permitAll());
	}
}
