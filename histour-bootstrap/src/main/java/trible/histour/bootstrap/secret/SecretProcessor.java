package trible.histour.bootstrap.secret;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;

@Slf4j
public class SecretProcessor implements EnvironmentPostProcessor {
		private static final String PROFILE_SEPARATOR = ",";
		private static final String HISTOUR_SECRET_PROPERTY_NAME = "histourApplicationSecretProperty";
		private static final String PROPERTY_SECRET_MANAGER_TOKEN_NAME = "SECRET_MANAGER_TOKEN";
		private static final String PROPERTY_SECRET_MANAGER_WORKSPACE_ID = "SECRET_MANAGER_WORKSPACE_ID";
		private static final String BEARER_TOKEN_PREFIX = "Bearer";
		private static final String SECRET_MANAGER_URL = "https://app.infisical.com/api/v3";
		private static final Set<String> SUPPORT_PROFILES = Set.of("local", "dev", "prod");

		@Override
		public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
				val secrets = getSecrets(environment);
				val histourSecretProperties = new MapPropertySource(
								HISTOUR_SECRET_PROPERTY_NAME,
								secrets.stream().collect(Collectors.toMap(Secret::secretKey, Secret::secretValue)));
				environment.getPropertySources().addLast(histourSecretProperties);
		}

		private List<Secret> getSecrets(ConfigurableEnvironment environment) {
				val activeProfiles = Arrays.stream(environment.getActiveProfiles()).toList();
				log.info("### ACTIVE PROFILES: {} ###", String.join(PROFILE_SEPARATOR, activeProfiles));

				val secretToken = getPropertyOrThrow(environment, PROPERTY_SECRET_MANAGER_TOKEN_NAME);
				val workspaceId = getPropertyOrThrow(environment, PROPERTY_SECRET_MANAGER_WORKSPACE_ID);
				val currentEnvironment = activeProfiles.stream()
								.filter(SUPPORT_PROFILES::contains)
								.findFirst()
								.orElseThrow(() -> new HistourException(
												ExceptionCode.INTERNAL_SERVER_ERROR,
												"Profiles: " + activeProfiles));

				val response = secretManagerRestClient(secretToken).get()
								.uri(uriBuilder -> uriBuilder
												.path("/secrets/raw")
												.queryParam("workspaceId", workspaceId)
												.queryParam("environment", currentEnvironment)
												.build())
								.retrieve()
								.body(SecretResponse.class);

				return response != null ? response.secrets : List.of();
		}

		private String getPropertyOrThrow(ConfigurableEnvironment environment, String propertyName) {
				String property = environment.getProperty(propertyName, String.class);
				if (property == null) {
						throw new HistourException(ExceptionCode.INTERNAL_SERVER_ERROR, "SECRET_MANAGER_CONFIG_NOT_SET");
				}
				return property;
		}

		private RestClient secretManagerRestClient(String secretToken) {
				return RestClient.builder()
								.defaultHeaders(headers ->
												headers.add(HttpHeaders.AUTHORIZATION, BEARER_TOKEN_PREFIX + " " + secretToken))
								.baseUrl(SECRET_MANAGER_URL)
								.build();
		}

		private record SecretResponse(
						List<Secret> secrets
		) {
		}

		private record Secret(
						String id,
						String workspace,
						String environment,
						Long version,
						String secretKey,
						String secretValue
		) {
		}
}
