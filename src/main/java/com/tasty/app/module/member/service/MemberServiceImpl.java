package com.tasty.app.module.member.service;

import com.tasty.app.infra.file.util.FileUtils;
import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.AddForm;
import com.tasty.app.module.member.form.EditForm;
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

    @Value("${upload.dir.member}")
    private String uploadDirMember;

    private final MemberRepository memberRepository;
    private final FileUtils fileUtils;

    @Override
    public boolean isExistsEmail(String email) {
        Member findMember = memberRepository.findMemberByEmail(email);
        return findMember != null;
    }

    @Override
    public int addMember(AddForm form) {
        // 중복확인
        Member findMember = memberRepository.findMemberByEmail(form.getEmail());
        if (findMember != null) {
            return -1;
        }

        // 비번 암호 (spring security)
        Member member = Member.toMemberFromAddForm(form);
        memberRepository.save(member);
        return 1;
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
        return  "/images/member/" + fileUtils.uploadFile(uploadDirMember, multipartFile, false);
    }

    @Override
    public String uploadTempImage(MultipartFile multipartFile) {
        return  "/images/member/" + fileUtils.uploadFile(uploadDirMember, multipartFile, true);
    }

    @Override
    public void deleteImage(String uploadFileName) {
        fileUtils.deleteFile(uploadDirMember, uploadFileName);
    }

    @Override
    public void removeMemberShip(String email) {
        memberRepository.deleteMemberShip(email);
    }

    @Override
    public boolean isExistsNickName(String email, String nickName) {
        int res = memberRepository.findMemberByNickName(email, nickName);
        return res > 0;
    }
}
