package org.demo.app.web.login.repository;

import org.demo.app.web.member.domain.Member;

public interface LoginRepository {

    // 회원조회
    // email로 조회
    Member findMemberByEmail(String email);
}
