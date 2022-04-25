package com.example.monolithic.repository;

import com.example.monolithic.dto.response.GroupMemberResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.monolithic.repository.QGroupMember.groupMember;
import static com.example.monolithic.repository.QGroup.group;
import static com.example.monolithic.repository.QMember.member;

@RequiredArgsConstructor
@Repository
public class GroupMemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<GroupMemberResponseDto> getGroupMemberList(Pageable pageable, Long groupId){
        QueryResults<GroupMemberResponseDto> query = jpaQueryFactory
                .select(Projections.fields(GroupMemberResponseDto.class,
                        groupMember.id,
                        member.id.as("memberId"),
                        member.email,
                        group.id.as("groupId"),
                        group.groupName,
                        groupMember.createdAt
                ))
                .from(groupMember)
                .join(groupMember.group, group)
                .join(groupMember.member, member)
                .where(groupMember.group.id.eq(groupId))
                .orderBy(groupMember.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(query.getResults(), pageable, query.getTotal());
    }
}
