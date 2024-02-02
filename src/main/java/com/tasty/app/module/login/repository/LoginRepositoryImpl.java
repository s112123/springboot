package com.tasty.app.module.login.repository;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.repository.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member login(String email) {
        Member member = memberMapper.selectOneByEmail(email);
        return member;
    }
}
