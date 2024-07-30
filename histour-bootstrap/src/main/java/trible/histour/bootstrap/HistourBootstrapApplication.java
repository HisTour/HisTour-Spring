package trible.histour.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "trible.histour")
public class HistourBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistourBootstrapApplication.class, args);
	}

}
