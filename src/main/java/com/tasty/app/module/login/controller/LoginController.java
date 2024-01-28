package com.tasty.app.module.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        return "sign_in";
    }
}
