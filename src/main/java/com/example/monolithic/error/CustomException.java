package com.example.monolithic.error;

import com.example.monolithic.enums.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorStatus errorStatus;
    private String message;

    public CustomException(ErrorStatus errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public CustomException(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }
}