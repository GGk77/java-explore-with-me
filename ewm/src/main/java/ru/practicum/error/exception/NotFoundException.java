package ru.practicum.error.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String error) {
        super(error);
    }
}
