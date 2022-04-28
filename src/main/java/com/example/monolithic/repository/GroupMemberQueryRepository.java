package com.example.monolithic.repository;

import com.example.monolithic.dto.request.SearchRequestDto;
import com.example.monolithic.dto.response.GroupMemberResponseDto;
import com.example.monolithic.dto.response.GroupResponseDto;
import com.example.monolithic.enums.Keyword;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import static com.example.monolithic.repository.QGroupMember.groupMember;
import static com.example.monolithic.repository.QGroup.group;
import static com.example.monolithic.repository.QMember.member;

@RequiredArgsConstructor
@Repository
public class GroupMemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<GroupMemberResponseDto> getGroupMemberList(Pageable pageable, Long groupId, SearchRequestDto searchRequestDto){
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
                .where(
                        groupEq(groupId),
                        keywordEq(searchRequestDto)
                )
                .orderBy(groupMember.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(query.getResults(), pageable, query.getTotal());
    }

    public Page<GroupResponseDto> getGroupList(Pageable pageable){
        QueryResults<GroupResponseDto> query = jpaQueryFactory
                .select(Projections.fields(GroupResponseDto.class,
                        group.id,
                        group.groupName,
                        group.createdAt
                ))
                .from(group)
                .orderBy(group.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(query.getResults(), pageable, query.getTotal());
    }

    public BooleanExpression groupEq(Long groupId){
        return groupMember.group.id.eq(groupId);
    }

    public BooleanExpression keywordEq(SearchRequestDto searchRequestDto){
        if(!ObjectUtils.isEmpty(searchRequestDto.getKeyword())){
            if(searchRequestDto.getKeyword() == Keyword.ID){
                return member.loginId.eq(searchRequestDto.getKeyValue());
            }
        }
        return null;
    }

}
