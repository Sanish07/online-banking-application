package com.sanish.loans.controllers;

import com.sanish.loans.constants.LoansConstants;
import com.sanish.loans.dto.LoansDto;
import com.sanish.loans.dto.ResponseDto;
import com.sanish.loans.entities.Loans;
import com.sanish.loans.services.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    private ILoansService loansService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewLoan(@RequestParam String mobileNumber) {
        loansService.createLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber) {
        LoansDto fetchedLoanDetails = loansService.getLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fetchedLoanDetails);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@RequestBody LoansDto loansDto) {
        boolean entryUpdated = loansService.updateLoan(loansDto);

        if(!entryUpdated) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam String mobileNumber) {
        boolean entryDeleted = loansService.deleteLoan(mobileNumber);

        if(!entryDeleted) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
    }
}
