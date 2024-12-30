package com.sanish.cards.services;

import com.sanish.cards.dto.CardsDto;


public interface ICardsService {

    /**
     * @param mobileNumber - Input mobile number of customer
     */
    void createCard(String mobileNumber);

    /**
     * @param mobileNumber - Input mobile number of customer
     * @return Card details of the customer
     */
    CardsDto fetchCard(String mobileNumber);


    /**
     * @param cardsDto - CardsDto object
     * @return Boolean value indicating if card details were updated or not
     */
    boolean updateCard(CardsDto cardsDto);


    /**
     * @param mobileNumber - Input mobile number of customer
     * @return Boolean value indicating if card details were deleted or not
     */
    boolean deleteCard(String mobileNumber);
}
