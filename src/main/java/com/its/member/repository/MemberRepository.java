package com.its.member.repository;

import com.its.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // select * from member_table where memberEmail = ?
    // 리턴타입 : MemberEntity
    // 매개변수 : memberEmail(String)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
