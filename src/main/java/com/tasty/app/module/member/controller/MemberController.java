package com.tasty.app.module.member.controller;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.form.MemberForm;
import com.tasty.app.module.member.form.EditForm;
import com.tasty.app.module.member.service.MemberService;
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

    private final MemberService memberService;

    // 회원 등록 화면
    @GetMapping("/add")
    public String addForm(MemberForm memberForm) {
        return "sign_up";
    }

    // 회원 등록 처리
    @PostMapping("/add")
    public String addMember(MemberForm memberForm) {
        memberService.addMember(memberForm);
        return "redirect:/";
    }

    // 회원 조회 화면
    @GetMapping("/view/{email}")
    public String viewMember(@PathVariable("email") String email, Model model) {
        Member member = memberService.getMemberByEmail(email);
        model.addAttribute("member", member);
        return "my/profile";
    }
}
