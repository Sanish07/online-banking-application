package com.sanish.cards.controllers;

import com.sanish.cards.constants.CardsConstants;
import com.sanish.cards.dto.CardsDto;
import com.sanish.cards.dto.ResponseDto;
import com.sanish.cards.services.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private ICardsService cardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewCard(@RequestParam String mobileNumber) {
        cardsService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> getCardDetails(@RequestParam String mobileNumber) {
        CardsDto response = cardsService.fetchCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
