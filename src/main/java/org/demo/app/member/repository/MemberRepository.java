package org.demo.app.member.repository;

import org.demo.app.member.domain.Member;

public interface MemberRepository {

    // 회원등록
    Long save(Member member);
}
