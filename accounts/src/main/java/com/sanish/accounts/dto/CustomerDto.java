package com.sanish.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account details"
)
@Data
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "Phil Jacobs")
    @NotEmpty(message = "Name field cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5-30")
    private String name;

    @Schema(description = "Email of the customer", example = "jacobs91@gmail.com")
    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email field should be a valid value")
    private String email;

    @Schema(description = "Mobile Number of customer", example = "1029384756")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    private AccountsDto accountsDto;

}
