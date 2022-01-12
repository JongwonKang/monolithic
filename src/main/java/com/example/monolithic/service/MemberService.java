package com.example.monolithic.service;

import com.example.monolithic.dto.request.MemberRequestDto;
import com.example.monolithic.dto.response.MemberResponseDto;
import com.example.monolithic.enums.ErrorStatus;
import com.example.monolithic.error.CustomException;
import com.example.monolithic.repository.Member;
import com.example.monolithic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto signupMember(MemberRequestDto memberRequestDto){
        Member admin = memberRequestDto.toAdmin(passwordEncoder);
        System.out.println(" : " + this);
        if (adminRepository.existsByLoginId(memberRequestDto.getLoginId())) {
            throw new CustomException(ErrorStatus.RESOURCE_ALREADY_EXISTS);
        }
        return new MemberResponseDto(adminRepository.save(admin));
    }
}
