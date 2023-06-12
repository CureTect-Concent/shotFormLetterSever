package com.shotFormLetter.sFL.domain.member.domain;

import com.shotFormLetter.sFL.domain.post.domain.entity.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByUserName(String userName);
}
