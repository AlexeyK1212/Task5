package ru.project.task5.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.task5.dto.*;
import ru.project.task5.other.ErrorMessages;
import ru.project.task5.services.*;
//import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    private final AccountService accountService;


    @Autowired
    private ErrorMessages errorMessages;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/account")
    public ResponseEntity<?> create(@Valid @RequestBody AccountDto accountDto) throws NoSuchFieldException, IllegalAccessException {// throws NoSuchFieldException, IllegalAccessException {
        accountService.create(accountDto);

        if( errorMessages.err!="")
            return new ResponseEntity<>( errorMessages.err,errorMessages.st);

        return new ResponseEntity<>( errorMessages.ok,HttpStatus.OK);


    }


}