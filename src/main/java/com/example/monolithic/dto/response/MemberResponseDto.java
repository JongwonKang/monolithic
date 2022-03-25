package com.example.monolithic.dto.response;

import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class MemberResponseDto {
    private String loginId;
    private String password;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @QueryProjection
    public MemberResponseDto(Member admin){
        this.loginId = admin.getLoginId();
        this.authority = admin.getAuthority();
    }
}
