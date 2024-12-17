package com.sanish.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(description = "Account number of Aero Bank account", example = "9128736450")
    @NotEmpty(message = "Account number must not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be of 10 digits")
    private Long accountNumber;

    @Schema(description = "Account type of Aero Bank Account", example = "SAVINGS")
    @NotEmpty(message = "Account type must not be null or empty")
    private String accountType;

    @Schema(description = "Aero Bank Branch Address", example = "21 lane, Springfield, New York")
    @NotEmpty(message = "Branch address must not be null or empty")
    private String branchAddress;

}
