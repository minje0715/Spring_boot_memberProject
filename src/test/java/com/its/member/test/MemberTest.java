package com.its.member.test;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService memberService;

    public MemberDTO newMember(int i) {
       MemberDTO member =
               new MemberDTO("테스트용이메일"+i, "테스트용비밀번호"+i, "테스트용이름"+i, 1+i, "테스트용전화번호"+i);
       return member;
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("회원가입테스트")
    public void memberSaveTest() {
//        MemberDTO member = new MemberDTO("테스트용이메일", "테스트용비밀번호", "테스트용이름", 99, "테스트용전화번호");
//        Long savedId = memberService.save(member);
    Long savedId = memberService.save(newMember(1));
    MemberDTO memberDTO = memberService.findById(savedId);
    assertThat(newMember(1).getMemberEmail()).isEqualTo((memberDTO.getMemberEmail()));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("로그인 테스트")
    public void loginTest() {
        String memberEmail = "로그인용이메일";
        String memberPassword = "로그인용비번";
        String memberName = "로그인용이름";
        int memberAge = 99;
        String memberMobile = "로그인용전화번호";
       MemberDTO memberDTO = new MemberDTO(memberEmail, memberPassword, memberName, memberAge, memberMobile);
        Long savedId = memberService.save(memberDTO);
        // 로그인 객체 생성 후 로그인
        MemberDTO loginMemberDTO = new MemberDTO();
        loginMemberDTO.setMemberEmail(memberEmail);
        loginMemberDTO.setMemberPassword(memberPassword);
        MemberDTO loginResult = memberService.login(loginMemberDTO);
        //로그인 결과가 not null 이면 테스트 통과
        assertThat(loginResult).isNotNull();
    }

    @Test
    @DisplayName("회원 데이터 저장")
    public void memberSave() {
        IntStream.rangeClosed(1,20).forEach(i-> {
            memberService.save(newMember(i));
        });
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원 삭제 테스트")
    public void memberDeleteTest() {
        /**
         * 신규 회원 등록
         * 삭제 처리
         * 해당 회원으로 조회시 null이면 통과
         */
        Long savedId = memberService.save(newMember(999));
        memberService.deleteById(savedId);
        assertThat(memberService.findById(savedId)).isNull();
    }
}
