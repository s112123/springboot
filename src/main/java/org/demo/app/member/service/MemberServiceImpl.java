package org.demo.app.member.service;

import lombok.RequiredArgsConstructor;
import org.demo.app.member.domain.Member;
import org.demo.app.member.dto.MemberForm;
import org.demo.app.member.exception.DuplicateEmailException;
import org.demo.app.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long addMember(MemberForm memberForm) {
        isDuplicatedEmail(memberForm);

        String encrypted = BCrypt.hashpw(memberForm.getPassword(), BCrypt.gensalt());
        memberForm.setPassword(encrypted);

        Member member = memberForm.toMember();
        Long memberId = memberRepository.save(member);
        return memberId;
    }

    private void isDuplicatedEmail(MemberForm memberForm) {
        int count = memberRepository.countByEmail(memberForm.getEmail());
        if (count > 0) {
            throw new DuplicateEmailException("이미 가입된 이메일 입니다");
        }
    }
}
