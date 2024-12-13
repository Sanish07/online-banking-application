package com.sanish.accounts.controllers;

import com.sanish.accounts.constants.AccountsConstants;
import com.sanish.accounts.dto.CustomerDto;
import com.sanish.accounts.dto.ResponseDto;
import com.sanish.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
//Through produces attr. we are explicitly mentioning, this controller supports response type of JSON format
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

        accountsService.createAccount(customerDto); //If everything went well, we will move on to next line
        //Or we will be facing some business logic exception handled at global exception handler

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
