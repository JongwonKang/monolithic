package com.example.monolithic.dto.response;

import com.example.monolithic.repository.GroupMember;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMemberResponseDto {
    private Long id;
    private Long memberId;
    private Long groupId;
    private String groupName;
    private String email;

    public GroupMemberResponseDto(GroupMember groupMember) {
        this.id = groupMember.getId();
        this.memberId = groupMember.getMember().getId();
        this.groupId = groupMember.getGroup().getId();
        this.groupName = groupMember.getGroup().getGroupName();
        this.email = groupMember.getMember().getEmail();
    }
}
