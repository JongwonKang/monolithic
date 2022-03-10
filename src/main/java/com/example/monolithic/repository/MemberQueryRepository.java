package com.example.monolithic.repository;

import com.example.monolithic.dto.response.MemberResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private QMember member = QMember.member;
    public Page<MemberResponseDto> getMemberList(Pageable pageable){
        QueryResults<MemberResponseDto> query = jpaQueryFactory
                .select(Projections.fields(MemberResponseDto.class,
                        member.email
                ))
                .from(member)
                .fetchResults();
        return new PageImpl<>(query.getResults(), pageable, query.getTotal());
    }
}
