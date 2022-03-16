package com.example.monolithic.service;

import com.example.monolithic.dto.PageMetadata;
import com.example.monolithic.dto.request.MemberRequestDto;
import com.example.monolithic.dto.response.ListResponseDTO;
import com.example.monolithic.dto.response.MemberResponseDto;
import com.example.monolithic.enums.ErrorStatus;
import com.example.monolithic.error.CustomException;
import com.example.monolithic.repository.Member;
import com.example.monolithic.repository.MemberQueryRepository;
import com.example.monolithic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.example.monolithic.util.MapperUtil.map;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public MemberResponseDto signupMember(MemberRequestDto memberRequestDto){
        Member admin = memberRequestDto.toMember(passwordEncoder);
        if (memberRepository.existsByLoginId(memberRequestDto.getLoginId())) {
            throw new CustomException(ErrorStatus.RESOURCE_ALREADY_EXISTS);
        }

        return new MemberResponseDto(memberRepository.save(admin));
    }

    @Transactional(readOnly = true)
    public ListResponseDTO<List<MemberResponseDto>> getMemberList(Pageable page){
        Page<MemberResponseDto> getMemberList = memberQueryRepository.getMemberList(page);

        return new ListResponseDTO<>(getMemberList.getContent(), map(getMemberList, PageMetadata.class));
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Member member){
        return map(memberRepository.findByLoginId(member.getLoginId()), MemberResponseDto.class);
    }
}
