package com.tasty.app.module.member.controller;

import com.tasty.app.module.member.form.AddForm;
import com.tasty.app.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 등록 화면
    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") AddForm form) {
        return "sign_up";
    }
}
