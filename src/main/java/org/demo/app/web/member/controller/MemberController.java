package org.demo.app.web.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    // 회원가입
    @GetMapping("/add")
    public String memberJoinForm() {
        return "member/add";
    }

    // 회원목록
    @GetMapping("/list")
    public String memberList() {
        return null;
    }
}
