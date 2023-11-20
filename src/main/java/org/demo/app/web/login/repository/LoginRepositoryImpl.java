package org.demo.app.web.login.repository;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.login.dto.LoginForm;
import org.demo.app.web.login.repository.mapper.LoginMapper;
import org.demo.app.web.member.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final LoginMapper loginMapper;

    @Override
    public Member findMemberByEmail(String email) {
        Member member = loginMapper.selectOneByEmail(email);
        return member;
    }
}
