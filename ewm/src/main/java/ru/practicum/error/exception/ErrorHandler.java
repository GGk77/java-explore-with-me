package ru.practicum.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.error.ApiError;
import ru.practicum.error.ErrorStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ApiError handleNotFoundException(final NotFoundException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.NOT_FOUND)
                .reason("NOT_FOUND")
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiError handleInternalServerErrorException(final RuntimeException e) {
        log.trace("trace");
        return ApiError.builder()
                .errors(Collections.singletonList(parse(e)))
                .message(e.getMessage())
                .status(ErrorStatus.INTERNAL_SERVER_ERROR)
                .reason("INTERNAL_SERVER_ERROR")
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiError handleBadRequestException(final BadRequestException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.BAD_REQUEST)
                .reason("BAD_REQUEST")
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ApiError handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(ErrorStatus.CONFLICT)
                .reason("CONFLICT")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ApiError.builder()
                .status(ErrorStatus.BAD_REQUEST)
                .message(e.getMessage())
                .reason("BAD_REQUEST")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiError handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ApiError.builder()
                .status(ErrorStatus.BAD_REQUEST)
                .message(e.getMessage())
                .reason("BAD_REQUEST")
                .build();
    }

    static String parse(Throwable exception) {
        StringWriter writer = new StringWriter();
        PrintWriter printer = new PrintWriter(writer);
        exception.printStackTrace(printer);
        return writer.toString();
    }
}
