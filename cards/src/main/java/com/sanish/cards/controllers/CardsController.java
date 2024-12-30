package com.sanish.cards.controllers;

import com.sanish.cards.constants.CardsConstants;
import com.sanish.cards.dto.CardsDto;
import com.sanish.cards.dto.ResponseDto;
import com.sanish.cards.services.ICardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private ICardsService cardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewCard(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be of 10 digits")
                                                         String mobileNumber) {
        cardsService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> getCardDetails(@RequestParam
                                                       @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be of 10 digits")
                                                       String mobileNumber) {
        CardsDto response = cardsService.fetchCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);

        if(!isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be of 10 digits")
                                                      String mobileNumber) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);

        if(!isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }
}
