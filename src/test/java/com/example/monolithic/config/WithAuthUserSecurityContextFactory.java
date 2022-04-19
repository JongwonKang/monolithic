package com.example.monolithic.config;

import com.example.monolithic.component.MemberAdapter;
import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithAuthUserSecurityContextFactory implements WithSecurityContextFactory<WithAuthUser> {
    @Override
    public SecurityContext createSecurityContext(WithAuthUser annotation) {
        String loginId = annotation.loginId();
        String email = annotation.email();
        Member authUser = new Member(loginId, "test", "test", Authority.USER);
        MemberAdapter adapter = new MemberAdapter(authUser);
        UsernamePasswordAuthenticationToken resultAuthenticationToken =
                new UsernamePasswordAuthenticationToken(adapter, "test", adapter.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(resultAuthenticationToken);

        return context;
    }
}
