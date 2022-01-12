package com.example.monolithic.dto.request;

import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class MemberRequestDto {
    private String loginId;
    private String password;
    @Enumerated(EnumType.STRING) //TODO
    private Authority authority;


    public Member toAdmin(PasswordEncoder passwordEncoder){
        return Member.builder()
                .loginId(this.loginId)
                .password(passwordEncoder.encode(this.password))
                .authority(Authority.ROLE_ADMIN)
                .build();
    }
}
