package com.random.randomImages.controller;

public class RandomLinkException extends RuntimeException {
    public RandomLinkException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
