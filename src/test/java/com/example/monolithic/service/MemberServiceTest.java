package com.example.monolithic.service;

import com.example.monolithic.dto.request.MemberRequestDto;
import com.example.monolithic.repository.Member;
import com.example.monolithic.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @InjectMocks //service 객체가 만들어질때 @Mock의 객체를 주입
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void createMember(){
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setLoginId("member");
        memberRequestDto.setPassword("password");

        Member member = Member.builder().loginId(memberRequestDto.getLoginId()).password(memberRequestDto.getPassword()).build();

        when(memberRepository.save(any())).thenReturn(member);
        given(memberRepository.findByLoginId(member.getLoginId())).willReturn(Optional.ofNullable(member));

        String loginId = memberService.signupMember(memberRequestDto).getLoginId();
        Member findMember = memberRepository.findByLoginId(loginId).get();

        System.out.println(findMember.getLoginId());

    }
}
