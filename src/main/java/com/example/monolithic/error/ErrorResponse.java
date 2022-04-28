package com.example.monolithic.error;

import com.example.monolithic.enums.ErrorStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(CustomException customException) {
        return ResponseEntity
                .status(customException.getErrorStatus().getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(customException.getErrorStatus().getHttpStatus().value())
                        .error(customException.getErrorStatus().getHttpStatus().name())
                        .code(customException.getErrorStatus().name())
                        .message(customException.getMessage())
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorStatus errorStatus) {
        return ResponseEntity
                .status(errorStatus.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorStatus.getHttpStatus().value())
                        .error(errorStatus.getHttpStatus().name())
                        .code(errorStatus.name())
                        .message(errorStatus.getMsg())
                        .build()
                );
    }
}