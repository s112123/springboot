package com.tasty.app.module.member.service;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.AddForm;
import com.tasty.app.module.member.form.EditForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface MemberService {

    // 회원 이메일(아이디) 중복 확인
    boolean isExistsEmail(String email);

    // 회원 등록
    int addMember(AddForm form);
    
    // 회원 조회
    Member getMemberByEmail(String email);

    // 회원 정보 변경
    void editMember(String email, EditForm form);

    // 회원 프로필 이미지 업로드 (이미지 요청 경로: /review/image/** 반환)
    String uploadImage(MultipartFile multipartFile);

    // 회원 계정 삭제
    void removeMemberShip(String email);
}
