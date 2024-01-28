package com.tasty.app.module.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    
    // 회원 등록 화면
    @GetMapping("/add")
    public String addForm() {
        return "sign_up";
    }
}
