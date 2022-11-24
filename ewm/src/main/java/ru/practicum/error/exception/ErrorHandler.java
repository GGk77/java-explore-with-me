package ru.practicum.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.error.ApiError;
import ru.practicum.error.ErrorStatus;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ApiError handleNotFoundException(final NotFoundException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.NOT_FOUND)
                .reason("not found")
                .build();
    }

    @ExceptionHandler
    public ApiError handleInternalServerErrorException(final RuntimeException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.INTERNAL_SERVER_ERROR)
                .reason("server error")
                .build();
    }

    @ExceptionHandler
    public ApiError handleValidationException(final BadRequestException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.BAD_REQUEST)
                .reason("bad request")
                .build();
    }

    @ExceptionHandler
    public ApiError handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.CONFLICT)
                .reason("conflict")
                .build();
    }

}
