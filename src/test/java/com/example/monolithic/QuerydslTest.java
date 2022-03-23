package com.example.monolithic;

import com.example.monolithic.enums.Authority;
import com.example.monolithic.repository.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.example.monolithic.repository.QMember.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class QuerydslTest {
    @Autowired
    EntityManager em;

    @BeforeEach
    public void before(){
        Member member1 = new Member("member1", "member1@gmail.com", "1234", Authority.ADMIN);
        Member member2 = new Member("member2", "member1@gmail.com", "1234", Authority.ADMIN);
        Member member3 = new Member("member3", "member1@gmail.com", "1234", Authority.ADMIN);
        Member member4 = new Member("member4", "member1@gmail.com", "1234", Authority.ADMIN);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void test(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.loginId.eq("member1"))
                .fetchOne();

        assertThat(findMember.getLoginId()).isEqualTo("member1");
    }
}
