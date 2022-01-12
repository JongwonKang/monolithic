package com.example.monolithic.dto.request;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String loginId;
    private String password;
}
