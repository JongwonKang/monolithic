package com.example.monolithic.service;

import com.example.monolithic.dto.request.GroupMemberRequestDto;
import com.example.monolithic.dto.request.GroupRequestDto;
import com.example.monolithic.dto.response.GroupDetailResponseDto;
import com.example.monolithic.dto.response.GroupResponseDto;
import com.example.monolithic.enums.ErrorStatus;
import com.example.monolithic.enums.GroupMemberRole;
import com.example.monolithic.error.CustomException;
import com.example.monolithic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto){
        return new GroupResponseDto(groupRepository.save(groupRequestDto.toGroup()));
    }

    @Transactional
    public GroupDetailResponseDto joinGroup(GroupMemberRequestDto groupMemberRequestDto){
        return new GroupDetailResponseDto(groupMemberRepository.save(
                groupMemberRequestDto.toGroupMember(getMember(groupMemberRequestDto.getMemberId()), getGroup(groupMemberRequestDto.getGroupId()))));
    }

    @Transactional
    public void leaveGroup(GroupMemberRequestDto groupMemberRequestDto){
        GroupMember groupMember = groupMemberRepository.findByMemberAndGroup(getMember(groupMemberRequestDto.getMemberId()), getGroup(groupMemberRequestDto.getGroupId()))
                .orElseThrow(() -> new CustomException(ErrorStatus.NOT_FOUND_GROUP_MEMBER));
        if(groupMember.getRole() == GroupMemberRole.MASTER){
            throw new CustomException(ErrorStatus.NOT_LEAVE);
        }
        groupMemberRepository.delete(groupMember);
    }

    public Member getMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorStatus.NOT_FOUND_GROUP));
    }

    public Group getGroup(Long groupId){
        return groupRepository.findById(groupId).orElseThrow(() -> new CustomException(ErrorStatus.NOT_FOUND_MEMBER));
    }

}
