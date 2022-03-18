package com.example.monolithic.dto.request;

import com.example.monolithic.enums.GroupMemberRole;
import com.example.monolithic.repository.Group;
import com.example.monolithic.repository.GroupMember;
import com.example.monolithic.repository.Member;
import lombok.Getter;

@Getter
public class GroupMemberRequestDto {
    private Long groupId;
    private Long memberId;
    private String joinMessage;

    public GroupMember toGroupMember(Member member, Group group){
        return GroupMember.builder()
                .group(group)
                .member(member)
                .joinMessage(this.joinMessage)
                .role(GroupMemberRole.NORMAL)
                .build();
    }

    public GroupMember to(Member member, Group group){
        return new GroupMember(group, member, this.joinMessage, GroupMemberRole.NORMAL);
    }
}
