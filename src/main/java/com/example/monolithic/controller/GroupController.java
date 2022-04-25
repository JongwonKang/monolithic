package com.example.monolithic.controller;

import com.example.monolithic.dto.request.GroupRequestDto;
import com.example.monolithic.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity createGroup(@RequestBody GroupRequestDto groupRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.createGroup(groupRequestDto));
    }

    @PutMapping
    public ResponseEntity updateGroup(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity getGroup(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroup(id));
    }

    @GetMapping
    public ResponseEntity getGroupList(){
        return null;
    }

    @PostMapping("/join")
    public ResponseEntity joinGroup(){
        return null;
    }

    @PostMapping("/leave")
    public ResponseEntity leaveGroup(){
        return null;
    }
}
