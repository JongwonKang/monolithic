package com.example.monolithic.controller;

import com.example.monolithic.dto.request.MemberRequestDto;
import com.example.monolithic.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity getMember(){
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping
    public ResponseEntity signupMember(@RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signupMember(memberRequestDto));
    }

    @PutMapping
    public ResponseEntity updateMember(){
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
