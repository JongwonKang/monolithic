package com.example.monolithic.service;

import com.example.monolithic.dto.request.GroupRequestDto;
import com.example.monolithic.dto.response.GroupResponseDto;
import com.example.monolithic.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto){
        return new GroupResponseDto(groupRepository.save(groupRequestDto.toGroup()));
    }

}
