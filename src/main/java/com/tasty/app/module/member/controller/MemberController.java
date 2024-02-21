package com.tasty.app.module.member.controller;

import com.tasty.app.infra.cookie.CookieUtils;
import com.tasty.app.module.email.service.EmailServiceImpl;
import com.tasty.app.module.member.form.AddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final CookieUtils cookieUtils;

    // 회원 등록 화면
    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") AddForm form) {
        return "sign_up";
    }

    // 이메일 인증 페이지
    @GetMapping("/valid_email")
    public String validEmailPage(
            @RequestParam(value="email", required = false) String email,
            @RequestParam(value = "valid_id", required = false) String validId,
            Model model
    ) {
        if (email != null) {
            String validEmailId = EmailServiceImpl.validEmailIds.get(email);
            if (validEmailId != null) {
                if (validEmailId.equals(validId)) {
                    cookieUtils.makeCookie("validEmail", validEmailId, -1);
                    model.addAttribute("result", "이메일 인증이 완료되었습니다");
                } else {
                    model.addAttribute("result", "유효하지 않은 링크 또는 이메일 인증이 완료되지 않았습니다");
                }
            }
        } else {
            model.addAttribute("result", "유효하지 않은 링크 또는 이메일 인증이 완료되지 않았습니다");
        }
        return "valid_email";
    }
}
