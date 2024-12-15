package com.sanish.accounts.controllers;

import com.sanish.accounts.constants.AccountsConstants;
import com.sanish.accounts.dto.CustomerDto;
import com.sanish.accounts.dto.ResponseDto;
import com.sanish.accounts.services.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
//Through produces attr. we are explicitly mentioning, this controller supports response type of JSON format
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        accountsService.createAccount(customerDto); //If everything went well, we will move on to next line
        //Or we will be facing some business logic exception handled at global exception handler

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                               String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if(!isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
                                                                String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(!isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }


}
