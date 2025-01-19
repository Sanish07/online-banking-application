package com.sanish.accounts;

import com.sanish.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API Documentation",
				description = "Rest API documentation for Accounts Microservice of Aero Bank application",
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
) //Other than info attribute we also have externalDocs attr. to navigate to some another external article/resource
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
