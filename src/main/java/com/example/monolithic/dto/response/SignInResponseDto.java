package com.example.monolithic.dto.response;

import com.example.monolithic.dto.TokenDto;
import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto {
    private String loginId;
    private Authority authority;
    private Long roleId;
    private TokenDto tokenDto;

    public SignInResponseDto(Member admin, TokenDto tokenDto){
        this.loginId = admin.getLoginId();
        this.authority = admin.getAuthority();
        this.roleId = admin.getId();
        this.tokenDto = tokenDto;
    }
}
