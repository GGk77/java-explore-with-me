package ru.practicum.error.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String error) {
        super(error);
    }
}

