package trible.histour.output.postgresql.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"trible.histour.output.postgresql"})
@EntityScan(basePackages = {"trible.histour.output.postgresql"})
public class JpaConfig {
}
