package com.tasty.app.module.member.repository;

import com.tasty.app.module.member.domain.Member;

public interface MemberRepository {

    // 회원 등록
    Long save(Member member);
    
    // 이메일로 회원 조회
    Member findMemberByEmail(String email);

    // 회원 정보 변경 (DB에서 변경된 행 수 반환)
    Long edit(String email, Member member);

    // 회원 계정 삭제
    void deleteMemberShip(String email);
}
