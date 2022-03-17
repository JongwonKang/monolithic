package com.example.monolithic.service;

import com.example.monolithic.dto.request.GroupMemberRequestDto;
import com.example.monolithic.dto.request.GroupRequestDto;
import com.example.monolithic.dto.response.GroupDetailResponseDto;
import com.example.monolithic.dto.response.GroupResponseDto;
import com.example.monolithic.enums.ErrorStatus;
import com.example.monolithic.error.CustomException;
import com.example.monolithic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final MemberRepository memberRepository;

    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto){
        return new GroupResponseDto(groupRepository.save(groupRequestDto.toGroup()));
    }

    public GroupDetailResponseDto joinGroup(GroupMemberRequestDto groupMemberRequestDto){
        Member member = memberRepository.findById(groupMemberRequestDto.getMemberId()).orElseThrow(() -> new CustomException(ErrorStatus.NOT_FOUND_GROUP));
        Group group = groupRepository.findById(groupMemberRequestDto.getGroupId()).orElseThrow(() -> new CustomException(ErrorStatus.NOT_FOUND_MEMBER));
        return new GroupDetailResponseDto(groupMemberRepository.save(groupMemberRequestDto.toGroupMember(member, group)));
    }

}
