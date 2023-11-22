package org.demo.app.web.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.app.web.login.dto.LoginForm;
import org.demo.app.web.login.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid LoginForm loginForm,
            BindingResult bindingResult,
            HttpSession session,
            @RequestParam(defaultValue = "/") String redirectURI
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        boolean isLogin = loginService.isLogin(loginForm);

        // 로그인 성공시, 세션 생성
        if (isLogin) {
            session.setAttribute("login", loginForm.getEmail());
            return "redirect:" + redirectURI;
        } else {
            log.info("비밀번호가 일치하지 않습니다");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("login");
        return "redirect:/";
    }
}
