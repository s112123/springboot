package org.demo.app.web.login.controller;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.login.dto.LoginForm;
import org.demo.app.web.login.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, HttpSession session) {
        boolean isLogin = loginService.isLogin(loginForm);

        // 세션 생성
        if (isLogin) {
            session.setAttribute("login", loginForm.getEmail());
            return "redirect:/";
        }

        return "login";
    }
}
