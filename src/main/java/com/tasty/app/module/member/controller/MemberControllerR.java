package com.tasty.app.module.member.controller;

import com.tasty.app.infra.cookie.CookieUtils;
import com.tasty.app.module.email.service.EmailService;
import com.tasty.app.module.email.service.EmailServiceImpl;
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

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberControllerR {

    private final MemberService memberService;
    private final EmailService emailService;
    private final CookieUtils cookieUtils;

    // 아이디 중복 확인
    @PostMapping("/exists_email")
    public Map<String, Boolean> isExistsEmail(@RequestBody LoginForm form) {
        Map<String, Boolean> responseData = new HashMap<>();
        responseData.put("isExists", memberService.isExistsEmail(form.getEmail()));
        return responseData;
    }

    // 인증 메일 발송
    @PostMapping("/send_email")
    public Object sendEmail(@Validated @RequestBody LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("email")) {
            return bindingResult.getFieldError("email");
        }

        // 인증 메일 발송
        // 1. 인증 메일 링크에 이메일, 이메일 인증 uuid를 쿼리 스트링으로 보낸다 (ConcurrencyMap에 저장해둔다)
        // 2. 인증 링크를 클릭하면 uuid 값을 가지는 쿠키가 생성된다
        // 3. 회원등록시, 쿠키 값과 ConcurrencyMap에 저장된 값을 비교한다
        // 4. 비교해서 맞으면 회원가입 완료 및 ConcurrencyMap을 비우고, 그렇지 않으면 인증 미처리 메세지 발송
        emailService.sendEmailForValid(form.getEmail());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("send", true);
        responseData.put("defaultMessage", "인증 메일이 발송되었습니다.\n 메일 인증을 하신 후, 회원가입을 완료하세요.");
        return responseData;
    }

    // 회원 등록 처리
    @PostMapping
    public Object add(
            @Valid @RequestBody AddForm form, BindingResult bindingResult,
            @CookieValue(value = "validEmail", required = false) Cookie cookie) {
        Map<String, Object> responseData = new HashMap<>();

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        // 인증 확인: addMember를 중복검사가 아닌 인증 여부로 바꿔야 함
        // 회원등록시, 메일 인증 쿠키 값과 ConcurrencyMap에 저장된 값을 비교한다
        if (cookie == null) {
            responseData.put("defaultMessage", "메일 인증을 완료하십시오");
        } else {
            if (!EmailServiceImpl.validEmailIds.get(form.getEmail()).equals(cookie.getValue())) {
                responseData.put("defaultMessage", "메일 인증을 완료하십시오");
            } else {
                memberService.addMember(form);
                EmailServiceImpl.validEmailIds.remove(form.getEmail());
                cookieUtils.removeCookieByName("validEmail");
                responseData.put("defaultMessage", "success");
            }
        }

        return responseData;
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
