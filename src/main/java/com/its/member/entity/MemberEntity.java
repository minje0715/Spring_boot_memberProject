package com.its.member.entity;

import com.its.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "memberEmail",unique = true, length = 50)
    private String memberEmail;

    @Column(name = "memberPassword", length = 20)
    private String memberPassword;

    @Column(name = "memberName", length = 20)
    private String memberName;

    @Column(name = "memberAge")
    private int memberAge;

    @Column(name = "memberPhone", length = 30)
    private String memberPhone;

    public static MemberEntity toEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberAge(memberDTO.getMemberAge());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        return memberEntity;
    }
}
