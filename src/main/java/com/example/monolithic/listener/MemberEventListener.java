package com.example.monolithic.listener;

import com.example.monolithic.repository.MemberEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MemberEventListener {
    @Async
    @EventListener
    public void sendMail(MemberEvent event) {
        log.info("send mail{}", event.getMember());
    }
}
