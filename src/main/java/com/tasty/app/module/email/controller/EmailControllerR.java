package com.tasty.app.module.email.controller;

import com.tasty.app.module.email.service.EmailService;
import com.tasty.app.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailControllerR {

    private final EmailService emailService;
    private final MemberService memberService;

    // 이메일 발송
    @GetMapping("/temp-password/{toEmail}")
    public boolean sendTempPassword(@PathVariable("toEmail") String toEmail) {
        // 이메일이 존재하는지 여부
        boolean isExists = memberService.isExistsEmail(toEmail);
        if (isExists) {
            // 이메일 발송
            emailService.sendEmailForTempPassword(toEmail);
        }
        return isExists;
    }
}
