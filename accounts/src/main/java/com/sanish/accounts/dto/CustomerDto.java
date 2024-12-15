package com.sanish.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name field cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5-30")
    private String name;

    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email field should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
