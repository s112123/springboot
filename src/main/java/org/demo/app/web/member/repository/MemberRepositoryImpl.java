package org.demo.app.web.member.repository;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.member.domain.Member;
import org.demo.app.web.member.repository.mapper.MemberMapper;
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
    public Member findMemberById(Long memberId) {
        Member member = memberMapper.selectOneById(memberId);
        return member;
    }
}
