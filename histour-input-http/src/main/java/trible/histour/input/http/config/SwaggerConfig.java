package trible.histour.input.http.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.val;


@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openApi() {
		val info = new Info()
				.title("HISTOUR Swagger")
				.description("HISTOUR API Docs")
				.version("1.0.0");

		val jwtSchemeName = "jwtAuth";
		val securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
		val components = new Components()
				.addSecuritySchemes(jwtSchemeName, new SecurityScheme()
						.name(jwtSchemeName)
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT"));

		return new OpenAPI()
				.addSecurityItem(securityRequirement)
				.components(components)
				.info(info);
	}

}
