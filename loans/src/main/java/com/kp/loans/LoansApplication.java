package com.kp.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.loans.controller") })
@EnableJpaRepositories("com.eazybytes.loans.repository")
@EntityScan("com.eazybytes.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@ConfigurationPropertiesScan(value = "com.kp.loans.properties")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "SimpleBank Loans microservice REST API Documentation",
				version = "v1"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
