package com.example.monolithic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String id);
    boolean existsByLoginId(String loginId);
}
