package com.example.monolithic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {

    private String accessToken;
    private Long accessTokenExpiresIn;
    private String grantType;
    private String refreshToken;

    @Builder
    public TokenDto(String accessToken, Long accessTokenExpiresIn, String grantType, String refreshToken) {
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.grantType = grantType;
        this.refreshToken = refreshToken;
    }
}