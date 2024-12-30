package com.sanish.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be of 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})", message = "Card Number must be of 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card Type must not be a null or empty")
    private String cardType;

    @Positive(message = "Total Card Limit must be greater than zero")
    private Integer totalLimit;

    @PositiveOrZero(message = "Amount used so far must be greater than or equal to zero")
    private Integer amountUsed;

    @PositiveOrZero(message = "Total available amount must be greater than or equal to zero")
    private Integer availableAmount;
}
