package com.example.monolithic.repository;

import org.springframework.context.ApplicationEvent;

public class MemberEvent extends ApplicationEvent {
    private final Member member;

    public MemberEvent(Object source) {
        super(source);
        this.member = (Member) source;
    }

    public Member getMember() {
        return member;
    }
}
