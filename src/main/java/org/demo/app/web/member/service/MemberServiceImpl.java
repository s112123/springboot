package org.demo.app.web.member.service;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.member.domain.Member;
import org.demo.app.web.member.dto.MemberForm;
import org.demo.app.web.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long addMember(MemberForm memberForm) {
        // 비밀번호 암호화
        memberForm.encrypt(memberForm.getPassword());
        Member member = toMember(memberForm);
        Long memberId = memberRepository.save(member);
        return memberId;
    }

    @Override
    public MemberForm getMemberById(Long memberId) {
        Member member = memberRepository.findMemberById(memberId);
        MemberForm memberForm = toMemberForm(member);
        return memberForm;
    }
}
