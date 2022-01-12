package com.example.monolithic.service;

import com.example.monolithic.component.CustomAuthenticationProvider;
import com.example.monolithic.component.TokenProvider;
import com.example.monolithic.dto.TokenDto;
import com.example.monolithic.dto.request.SignInRequestDto;
import com.example.monolithic.dto.response.SignInResponseDto;
import com.example.monolithic.enums.ErrorStatus;
import com.example.monolithic.error.CustomException;
import com.example.monolithic.repository.Member;
import com.example.monolithic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberRepository adminRepository;
    private final TokenProvider tokenProvider;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    /**
     *
     * @param loginRequestDto
     * @return
     */
    public SignInResponseDto login(SignInRequestDto loginRequestDto){
        Member admin = adminRepository.findByLoginId(loginRequestDto.getLoginId())
                .orElseThrow(() -> new CustomException(ErrorStatus.INVALID_USER));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLoginId(), loginRequestDto.getPassword());
        Authentication authentication = customAuthenticationProvider.authenticate(authenticationToken);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        return new SignInResponseDto(admin, tokenDto);
    }

}
