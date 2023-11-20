package org.demo.app.web.login.service;

import org.assertj.core.api.Assertions;
import org.demo.app.web.login.dto.LoginForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class LoginServiceImplTest {

    @Autowired
    LoginService loginService;

    @Test
    @DisplayName("로그인 검증")
    void login() {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("a1234@naver.com");
        loginForm.setPassword("1234");

        boolean isLogin = loginService.isLogin(loginForm);
        assertThat(isLogin).isTrue();
    }
}