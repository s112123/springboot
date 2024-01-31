package com.tasty.app.module.member.service;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.EditForm;
import com.tasty.app.module.member.form.MemberForm;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

    // 회원 등록
    void addMember(MemberForm memberForm);
    
    // 회원 조회
    Member getMemberByEmail(String email);

    // 회원 정보 변경
    void editMember(String email, EditForm form);

    // 회원 프로필 이미지 업로드 (이미지 요청 경로: /review/image/** 반환)
    String uploadImage(MultipartFile multipartFile);
}
