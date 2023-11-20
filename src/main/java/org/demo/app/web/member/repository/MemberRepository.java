package org.demo.app.web.member.repository;

import org.demo.app.web.member.domain.Member;

public interface MemberRepository {

    // 회원등록
    // memberId 반환
    Long save(Member member);

    // 회원조회
    // memberId로 조회
    Member findMemberById(Long memberId);
}
