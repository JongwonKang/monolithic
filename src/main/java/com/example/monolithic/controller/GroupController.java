package com.example.monolithic.controller;

import com.example.monolithic.dto.request.GroupRequestDto;
import com.example.monolithic.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity createGroup(@RequestBody GroupRequestDto groupRequestDto){
        return  ResponseEntity.status(HttpStatus.OK).body(groupService.createGroup(groupRequestDto));
    }
}
