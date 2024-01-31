package com.tasty.app.module.member.service;

import com.tasty.app.infra.file.FileUtils;
import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.EditForm;
import com.tasty.app.module.member.form.MemberForm;
import com.tasty.app.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${member.upload.dir}")
    private String memberUploadDir;

    private final MemberRepository memberRepository;
    private final FileUtils fileUtils;

    @Override
    public void addMember(MemberForm memberForm) {
        // 중복확인
        Member findMember = memberRepository.findMemberByEmail(memberForm.getEmail());
        if (findMember != null) {
            log.info("이미 존재하는 이메일입니다");
            return;
        }

        // 비번 암호 (spring security)
        Member member = Member.toMemberFromMemberForm(memberForm);
        memberRepository.save(member);
    }

    @Override
    public Member getMemberByEmail(String email) {
        Member member = memberRepository.findMemberByEmail(email);
        return member;
    }

    @Override
    public void editMember(String email, EditForm form) {
        if (form.getPassword().equals("")) {
            // 기존의 비밀번호 유지
            Member member = getMemberByEmail(email);
            form.setPassword(member.getPassword());
        }

        Member member = Member.toMemberFromEditForm(form);
        Long res = memberRepository.edit(email, member);
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        return  "/members/image/" + fileUtils.uploadFile(memberUploadDir, multipartFile);
    }
}
