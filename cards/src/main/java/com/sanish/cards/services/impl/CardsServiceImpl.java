package com.sanish.cards.services.impl;

import com.sanish.cards.dto.CardsDto;
import com.sanish.cards.repositories.CardsRepository;
import com.sanish.cards.services.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param cardsDto - CardsDto object
     */
    @Override
    public void createCard(CardsDto cardsDto) {

    }

    /**
     * @param mobileNumber - Input mobile number of customer
     * @return Card details of the customer
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        return null;
    }

    /**
     * @param cardsDto - CardsDto object
     * @return Boolean value indicating if card details were updated or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        return false;
    }

    /**
     * @param mobileNumber - Input mobile number of customer
     * @return Boolean value indicating if card details were deleted or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}
