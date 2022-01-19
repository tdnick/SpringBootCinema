package com.example.cinema.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message + " could not be found!");
    }
}
