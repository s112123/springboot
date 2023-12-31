package org.demo.app.member.repository;

import lombok.RequiredArgsConstructor;
import org.demo.app.member.domain.Member;
import org.demo.app.member.repository.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

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
    public int countByEmail(String email) {
        int result = memberMapper.selectOneByEmail(email);
        return result;
    }
}
