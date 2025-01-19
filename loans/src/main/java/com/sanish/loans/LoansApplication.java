package com.sanish.loans;

import com.sanish.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Loans Microservice REST API Documentation",
                description = "Rest API documentation for Loans Microservice of Aero Bank application",
                version = "v1",
                contact = @Contact(
                        name = "Sanish Nirwan",
                        email = "sanishnirwan@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@SpringBootApplication
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
