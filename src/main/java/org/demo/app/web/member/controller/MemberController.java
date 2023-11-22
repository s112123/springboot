package org.demo.app.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.member.domain.Member;
import org.demo.app.web.member.dto.MemberForm;
import org.demo.app.web.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @GetMapping("/add")
    public String memberJoinForm(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "member/add";
    }

    @PostMapping("/add")
    public String memberJoin(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/add";
        }

        memberService.addMember(memberForm);
        return "redirect:/login";
    }

    // 회원목록
    @GetMapping("/list")
    public String memberList() {
        return null;
    }

    // 회원조회
    // 회원수정
    // 회원삭제
}
