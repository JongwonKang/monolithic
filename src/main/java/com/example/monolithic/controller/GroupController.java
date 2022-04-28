package com.example.monolithic.controller;

import com.example.monolithic.dto.request.GroupMemberRequestDto;
import com.example.monolithic.dto.request.GroupRequestDto;
import com.example.monolithic.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(groupRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGroup(@PathVariable Long id, @RequestBody GroupRequestDto groupRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.updateGroup(id, groupRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity getGroup(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroup(id));
    }

    @GetMapping
    public ResponseEntity getGroupList(@PageableDefault(page = 0, size = 5) Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupList(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity getGroupMemberList(@PageableDefault(page = 0, size = 5) Pageable page, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupMemberList(page, id));
    }

    @PostMapping("/join")
    public ResponseEntity joinGroup(@RequestBody GroupMemberRequestDto groupMemberRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.joinGroup(groupMemberRequestDto));
    }

    @PostMapping("/leave")
    public ResponseEntity leaveGroup(@RequestBody GroupMemberRequestDto groupMemberRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.leaveGroup(groupMemberRequestDto));
    }
}
