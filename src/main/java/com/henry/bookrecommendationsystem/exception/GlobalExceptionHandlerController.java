package com.henry.bookrecommendationsystem.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ApiResponse> handleJWTVerificationException(JWTVerificationException exception) {
        log.info("GlobalExceptionHandlerController: handleJWTVerificationException() happened");
        return new ResponseEntity<>(new ApiResponse(false, LocalDateTime.now().toString(),
                exception.getMessage(), null), HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.info("GlobalExceptionHandlerController: handleDataIntegrityViolationException() happened");
        return new ResponseEntity<>(new ApiResponse(false, LocalDateTime.now().toString(),
                Arrays.stream(Objects.requireNonNull(exception.getMessage()).split(";")).findFirst().get(),
                exception.getMessage()), HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exception) {
        log.info("GlobalExceptionHandlerController: Exception() happened");
        return new ResponseEntity<>(new ApiResponse(false, LocalDateTime.now().toString(),
                exception.getMessage(), null), HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}