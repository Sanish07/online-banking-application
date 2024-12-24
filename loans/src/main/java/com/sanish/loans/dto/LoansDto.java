package com.sanish.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loans",
        description = "Schema to hold Customer's Loan information"
)
@Data
public class LoansDto {

    @Schema(description = "Mobile number of the customer", example = "9128736450")
    @NotEmpty(message = "Mobile number field must not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

    @Schema(description = "Loan number of customer", example = "789456123012")
    @NotEmpty(message = "Loan number field must not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be of 12 digits")
    private String loanNumber;

    @Schema(description = "Type of loan taken", example = "Business Loan")
    @NotEmpty(message = "Loan Type field must not be null or empty")
    private String loanType;

    @Schema(description = "Total loan amount taken", example = "250000")
    @Positive(message = "Total loan amount must be greater than zero")
    private Integer totalLoan;

    @Schema(description = "Total loan amount paid paid by customer so far", example = "50000")
    @PositiveOrZero(message = "Amount paid should be greater than or equal to zero")
    private Integer amountPaid;

    @Schema(description = "Total loan remaining(outstanding) to pay", example = "200000")
    @PositiveOrZero(message = "Total outstanding amount should be greater than or equal to zero")
    private Integer outstandingAmount;
}
