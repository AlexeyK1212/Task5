package ru.project.task5.other;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;



@ControllerAdvice

public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errors = ex.getBindingResult().getFieldErrors().stream()

                .map(fieldError -> fieldError.getDefaultMessage())

                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}