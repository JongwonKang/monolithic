package com.example.monolithic.repository;

import com.example.monolithic.enums.Authority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void ex(){
        final boolean exists = memberRepository.existsByLoginId("member1@gmail.com");
        assertThat(exists).isFalse();
    }

    @Test
    void ex2(){
        final Member member = memberRepository.save(new Member("member1", "member1@gmail.com", "1234", Authority.ADMIN));
        assertThat(member.getLoginId()).isEqualTo("member1");
    }

    @Test
    void ex3(){
        memberRepository.save(new Member("member1", "member1@gmail.com", "1234", Authority.ADMIN));
        final String loginId = memberRepository.findByLoginId("member1").get().getLoginId();
        assertThat(loginId).isEqualTo("member1");
    }
}
