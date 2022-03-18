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
    INTERNAL_SERVER_ERROR("serverError", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN("토큰 정보가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
    NOT_LEAVE( "관리자는 탈퇴할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_GROUP("그룹 정보가 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_GROUP_MEMBER("그룹 회원이 아닙니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_MEMBER("회원 정보가 없습니다.", HttpStatus.NOT_FOUND);
    private final String msg;
    private final HttpStatus httpStatus;

}
