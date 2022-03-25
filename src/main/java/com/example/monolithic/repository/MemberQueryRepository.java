package com.example.monolithic.repository;

import com.example.monolithic.dto.response.MemberResponseDto;
import com.example.monolithic.dto.response.QMemberResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.monolithic.repository.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<MemberResponseDto> getMemberList(Pageable pageable){
        QueryResults<MemberResponseDto> query = jpaQueryFactory
                /*.select(Projections.fields(MemberResponseDto.class,
                        member.email
                ))*/
                .select(new QMemberResponseDto(member))
                .from(member)
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(query.getResults(), pageable, query.getTotal());
    }
}
