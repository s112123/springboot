package org.demo.app.web.login.controller;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.login.dto.LoginForm;
import org.demo.app.web.login.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(LoginForm loginForm) {
        loginService.login(loginForm);
        return "login";
    }
}
