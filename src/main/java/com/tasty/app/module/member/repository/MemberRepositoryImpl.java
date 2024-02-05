package com.tasty.app.module.member.repository;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.repository.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Long save(Member member) {
        memberMapper.insertOne(member);
        return member.getMemberId();
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberMapper.selectOneByEmail(email);
    }

    @Override
    public Long edit(String email, Member member) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("member", member);
        return memberMapper.updateOneByEmail(params);
    }

    @Override
    public void deleteMemberShip(String email) {
        memberMapper.deleteMemberShip(email);
    }
}
