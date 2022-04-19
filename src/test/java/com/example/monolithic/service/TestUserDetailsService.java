package com.example.monolithic.service;

import com.example.monolithic.component.MemberAdapter;
import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TestUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = Member.builder().loginId("test").password("test").authority(Authority.USER).build();
        return new MemberAdapter(member);
    }
}