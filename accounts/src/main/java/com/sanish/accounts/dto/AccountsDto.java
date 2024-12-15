package com.sanish.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number must not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be of 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type must not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address must not be null or empty")
    private String branchAddress;

}
