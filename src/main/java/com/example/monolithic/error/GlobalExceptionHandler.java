package com.example.monolithic.error;

import com.example.monolithic.enums.ErrorStatus;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error("handleDataException throw Exception : {}", e);
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    protected ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        log.error("handleBadCredentialsException throw Exception : {}", e);
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException  e) {
        log.error("handleMethodArgumentNotValidException throw Exception : {}", e);
        log.error("error param : {}", e.getBindingResult());
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR);
    }

}
