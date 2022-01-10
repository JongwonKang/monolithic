package com.example.monolithic.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum ErrorStatus {

    RESOURCE_ALREADY_EXISTS( "이미 가입된 사용자 입니다.", HttpStatus.BAD_REQUEST),
    INVALID_USER("아이디를 확인해 주세요.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("아이디, 패스워드를 확인해 주세요", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("serverError", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String msg;
    private final HttpStatus httpStatus;

}
