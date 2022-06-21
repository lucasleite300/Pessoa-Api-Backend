package br.com.academia.pessoaapi;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PessoaApiApplication {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(PessoaApiApplication.class, args);
	}

	@Bean(initMethod = "migrate")
	Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setLocations("classpath:db/migration");
		flyway.setBaselineOnMigrate(true);
		flyway.setDataSource(url, user, password);
		return flyway;
	}
}
