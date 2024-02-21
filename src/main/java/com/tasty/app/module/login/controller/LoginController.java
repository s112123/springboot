package com.tasty.app.module.login.controller;

import com.tasty.app.infra.cookie.CookieUtils;
import com.tasty.app.module.login.form.LoginForm;
import com.tasty.app.module.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final CookieUtils cookieUtils;

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm(
            @RequestParam(value = "register", defaultValue = "false") boolean register,
            @CookieValue(value="remember", required=false) Cookie cookie,
            Model model
    ) {
        model.addAttribute("register", register);

        if (cookie != null) {
            model.addAttribute("remember", cookie.getValue());
        }

        return "sign_in";
    }

    // 로그인 처리
    @PostMapping("/login")
    @ResponseBody
    public Object login(
            @Validated @RequestBody LoginForm form, BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        // 로그인이 되면 세션 생성
        int res = loginService.login(form);
        if (res == 1) {
            // 세션 생성
            session.setAttribute("email", form.getEmail());

            // 아이디 저장 (쿠키)
            if (form.isRemember()) {
                // 체크박스가 체크되어 있으면 쿠키 생성
                cookieUtils.makeCookie("remember", form.getEmail(), 1);
            } else {
                // 체크박스가 체크되어 있지 않으면 쿠키 삭제
                cookieUtils.removeCookieByName("remember");
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("res", res);
        response.put("defaultMessage", "이메일 또는 비밀번호를 잘못 입력했습니다");
        return response;
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 삭제
        session.removeAttribute("email");
        return "redirect:/login";
    }
}
