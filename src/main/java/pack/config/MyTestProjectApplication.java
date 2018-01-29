package pack.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "pack.*" })
@EntityScan("pack.model")
@EnableJpaRepositories("pack.*")
public class MyTestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTestProjectApplication.class, args);
	}
}
