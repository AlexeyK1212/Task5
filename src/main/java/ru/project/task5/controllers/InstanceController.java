package ru.project.task5.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.task5.dto.*;
import ru.project.task5.other.ErrorMessages;
import ru.project.task5.services.*;


@RestController
public class InstanceController {

    private final InstanceService instanceService;

    @Autowired
    private ErrorMessages errorMessages;

    @Autowired
    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }


    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@Valid @RequestBody InstanceDto instanceDto) throws NoSuchFieldException, IllegalAccessException {

        instanceService.create(instanceDto);

        if(errorMessages.err!="")
            return new ResponseEntity<>(errorMessages.err,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessages.ok,HttpStatus.OK);


    }


}