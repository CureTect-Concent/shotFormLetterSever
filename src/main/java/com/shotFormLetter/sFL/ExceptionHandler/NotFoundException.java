package com.shotFormLetter.sFL.ExceptionHandler;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}