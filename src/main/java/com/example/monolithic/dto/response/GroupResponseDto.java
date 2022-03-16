package com.example.monolithic.dto.response;

import com.example.monolithic.repository.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponseDto {
    private Long id;
    private String groupName;

    public GroupResponseDto(Group group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
    }
}
