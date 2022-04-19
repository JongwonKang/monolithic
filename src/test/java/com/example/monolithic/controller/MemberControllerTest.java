package com.example.monolithic.controller;

import com.example.monolithic.config.SecurityConfig;
import com.example.monolithic.config.WithAuthUser;
import com.example.monolithic.dto.PageMetadata;
import com.example.monolithic.dto.response.ListResponseDTO;
import com.example.monolithic.dto.response.MemberResponseDto;
import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import com.example.monolithic.service.MemberService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.example.monolithic.util.MapperUtil.map;

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
        ResultActions action = mockMvc.perform(post("/member")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON).with(csrf())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andDo(print());

        action.andExpect(status().isCreated())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.[0].loginId").value("test"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getMemberList() throws Exception {
        List<MemberResponseDto> content = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        content.add(new MemberResponseDto("test", "test", Authority.USER));
        ListResponseDTO<List<MemberResponseDto>> memberList = new ListResponseDTO<>(content,map(content,PageMetadata.class));

        when(memberService.getMemberList(pageable)).thenReturn(memberList);
        ResultActions action = mockMvc.perform(get("/member?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(print());

        /*action.andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.[0].loginId").value("test"));*/
    }

    @Test
    @WithAuthUser(email = "test", loginId = "test")
    public void getMember() throws Exception{
        Member member = Member.builder().loginId("test").password("test").authority(Authority.USER).build();
        MemberResponseDto content = new MemberResponseDto(member);
        when(memberService.getMember(any())).thenReturn(content);
        ResultActions actions = mockMvc.perform(get("/member/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @Test
    public void test(){
        BigDecimal discountedPrice = new BigDecimal("50000");
        discountedPrice.toString();
    }
}
