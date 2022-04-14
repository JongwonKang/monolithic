package com.example.monolithic.controller;

import com.example.monolithic.config.SecurityConfig;
import com.example.monolithic.dto.response.MemberResponseDto;
import com.example.monolithic.enums.Authority;
import com.example.monolithic.service.MemberService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    public void signupMember() throws Exception {
        String json = objectMapper.writeValueAsString(new MemberResponseDto("test", "test", Authority.USER));
        mockMvc.perform(post("/member")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON).with(csrf())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andDo(print());
    }
}
