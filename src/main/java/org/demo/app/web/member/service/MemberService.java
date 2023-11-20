package org.demo.app.web.member.service;

import org.demo.app.web.member.domain.Member;
import org.demo.app.web.member.dto.MemberForm;

public interface MemberService {

    // 회원등록
    // memberId 반환
    Long addMember(MemberForm memberForm);

    // 회원조회
    MemberForm getMemberById(Long memberId);

    // MemberForm (DTO) → Member (Entity)
    default Member toMember(MemberForm memberForm) {
        return Member.builder()
                .memberId(memberForm.getMemberId())
                .email(memberForm.getEmail())
                .password(memberForm.getPassword())
                .username(memberForm.getUsername())
                .build();
    }

    // Member (Entity) → MemberForm (DTO)
    default MemberForm toMemberForm(Member member) {
       return MemberForm.builder()
               .memberId(member.getMemberId())
               .email(member.getEmail())
               .password(member.getPassword())
               .username(member.getUsername())
               .regDate(member.getRegDate())
               .modDate(member.getModDate())
               .build();
    }
}

