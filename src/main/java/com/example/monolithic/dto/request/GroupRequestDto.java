package com.example.monolithic.dto.request;

import com.example.monolithic.repository.Group;
import lombok.Getter;

@Getter
public class GroupRequestDto {
    private String groupName;

    public Group toGroup(){
        return Group.builder()
                .groupName(this.getGroupName())
                .build();
    }

}
