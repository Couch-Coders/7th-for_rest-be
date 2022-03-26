package couch.forrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ForrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForrestApplication.class, args);
	}

}
