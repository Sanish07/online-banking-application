package com.sanish.loans.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    @GetMapping("/hello")
    public ResponseEntity<Object> sayHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
