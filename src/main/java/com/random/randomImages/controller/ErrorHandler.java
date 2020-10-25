package com.random.randomImages.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(RandomLinkException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RandomLinkException handlerException(RandomLinkException ecommerceException) {
        return ecommerceException;
    }
}
