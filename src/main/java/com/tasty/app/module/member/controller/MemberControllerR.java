package com.tasty.app.module.member.controller;

import com.tasty.app.module.login.form.LoginForm;
import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.AddForm;
import com.tasty.app.module.member.form.EditForm;
import com.tasty.app.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberControllerR {

    private final MemberService memberService;

    // 아이디 중복 확인
    @PostMapping("/exists_email")
    public Map<String, Boolean> isExistsEmail(@RequestBody LoginForm form) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("isExists", memberService.isExistsEmail(form.getEmail()));
        return response;
    }

    // 인증 메일 발송
    @PostMapping("/send_email")
    public Object sendEmail(@Validated @RequestBody LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("email")) {
            return bindingResult.getFieldError("email");
        }

        // 이메일 코드 발송

        Map<String, Object> response = new HashMap<>();
        response.put("send", true);
        response.put("defaultMessage", "인증 메일이 발송되었습니다");
        return response;
    }

    // 회원 등록 처리
    @PostMapping
    public Object add(@Valid @RequestBody AddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        // 인증 확인: addMember를 중복검사가 아닌 인증 여부로 바꿔야 함
        int res = memberService.addMember(form);
        Map<String, Object> response = new HashMap<>();
        response.put("res", res);
        response.put("defaultMessage", "중복된 이메일입니다");
        return response;
    }

    // 회원 정보 수정
    @PatchMapping("/{email}")
    public String editMember(@PathVariable("email") String email, @RequestBody EditForm form) {
        memberService.editMember(email, form);
        return "updated";
    }

    // 닉네임 중복 여부
    @GetMapping("/check_nickname/{nickName}")
    public boolean checkDuplicateNickName(
            @SessionAttribute("email") String email, @PathVariable("nickName") String nickName
    ) {
        return memberService.isExistsNickName(email, nickName);
    }

    // 회원 프로필 이미지 저장
    @PostMapping("/image/save")
    public String saveProfileImage(
            @SessionAttribute("email") String email,
            @RequestPart(value = "profile-file", required = false) MultipartFile multipartFile
    ) {
        Member member = memberService.getMemberByEmail(email);

        if (multipartFile != null) {
            // 기존 파일 삭제
            String oldImageFileName = member.getFileName();
            memberService.deleteImage(oldImageFileName);

            // 프로필 이미지 저장
            String uploadUrl = memberService.uploadImage(multipartFile);
            return uploadUrl;
        }

        return member.getImageUrl();
    }

    // 회원 프로필 임시 이미지 저장
    @PostMapping("/temp_image/save")
    public String saveTempProfileImage(@RequestPart("profile-file") MultipartFile multipartFile) {
        String uploadUrl = memberService.uploadTempImage(multipartFile);
        return uploadUrl;
    }
}
