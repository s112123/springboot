package com.tasty.app.module.member.controller;

import com.tasty.app.module.member.form.EditForm;
import com.tasty.app.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberControllerR {

    private final MemberService memberService;

    // 회원 정보 수정
    @PatchMapping("/{email}")
    public String editMember(
            @PathVariable("email") String email, @RequestBody EditForm form
    ) {
        memberService.editMember(email, form);
        return "updated";
    }

    // 회원 프로필 이미지 저장
    @PostMapping("/image/save")
    public String saveProfileImage(@RequestPart("profile-file") MultipartFile multipartFile) {
        String uploadUrl = memberService.uploadImage(multipartFile);
        log.info("uploadUrl={}", uploadUrl);
        return uploadUrl;
    }
}
