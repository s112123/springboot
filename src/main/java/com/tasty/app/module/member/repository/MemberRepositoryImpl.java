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
        log.info("member={}", member);

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("member", member);

        memberMapper.updateOneByEmail(map);
        return member.getMemberId();
    }
}
