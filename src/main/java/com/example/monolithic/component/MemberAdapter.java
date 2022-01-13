package com.example.monolithic.component;

import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class MemberAdapter extends User {
    private Member member;

    public MemberAdapter(Member member){
        super(member.getLoginId(), member.getPassword(), test(member.getAuthority()));
        this.member = member;
    }

    private static Collection<? extends GrantedAuthority> test(Authority authority){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.name());
        return Collections.singleton(grantedAuthority);
    }

    public Member getMember(){
        return member;
    }
}
