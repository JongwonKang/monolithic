package com.example.monolithic.dto.response;

import com.example.monolithic.repository.GroupMember;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDetailResponseDto {
    private Long id;
    private String groupName;

    public GroupDetailResponseDto(GroupMember groupMember) {
        this.id = groupMember.getId();
        this.groupName = groupMember.getGroup().getGroupName();
    }
}
