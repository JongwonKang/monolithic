package com.example.monolithic.repository;

import com.example.monolithic.dto.response.GroupMemberResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.monolithic.repository.QGroupMember.groupMember;
import static com.example.monolithic.repository.QGroup.group;
import static com.example.monolithic.repository.QMember.member;

@RequiredArgsConstructor
@Repository
public class GroupMemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<GroupMemberResponseDto> getGroupMemberList(Long groupId){
        return jpaQueryFactory
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
                .fetch();
    }
}
