package com.example.monolithic.error;

import com.example.monolithic.enums.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorStatus errorCode;
}