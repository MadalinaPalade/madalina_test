package pack.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "pack.*" })
@EntityScan("pack.model")
@EnableJpaRepositories("pack.repository")
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
public class MyTestProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyTestProjectApplication.class, args);
	}
}
