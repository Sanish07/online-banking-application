package com.sanish.cards.services.impl;

import com.sanish.cards.constants.CardsConstants;
import com.sanish.cards.dto.CardsDto;
import com.sanish.cards.entities.Cards;
import com.sanish.cards.exceptions.CardAlreadyExistsException;
import com.sanish.cards.exceptions.ResourceNotFoundException;
import com.sanish.cards.mappers.CardsMapper;
import com.sanish.cards.repositories.CardsRepository;
import com.sanish.cards.services.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Input mobile number of customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with mobileNumber: " + mobileNumber);
        }
        Cards newCard = initiateCard(mobileNumber);
        cardsRepository.save(newCard);
    }

    private Cards initiateCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - Input mobile number of customer
     * @return Card details of the customer
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards fetchedCard = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));

        CardsDto response = CardsMapper.mapToCardsDto(fetchedCard, new CardsDto());
        return response;
    }

    /**
     * @param cardsDto - CardsDto object
     * @return Boolean value indicating if card details were updated or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards fetchedCard = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(()-> new ResourceNotFoundException("Cards", "cardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, fetchedCard);
        cardsRepository.save(fetchedCard);
        return true;
    }

    /**
     * @param mobileNumber - Input mobile number of customer
     * @return Boolean value indicating if card details were deleted or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards fetchedCard = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(fetchedCard.getCardId());
        return true;
    }
}
