package org.demo.app.web.login.service;

import org.demo.app.web.login.dto.LoginForm;

public interface LoginService {

    // 로그인
    // email로 회원을 조회하고 비밀번호 일치 여부를 확인
    // 로그인 되면 true, 로그인이 되지 않으면 false
    boolean isLogin(LoginForm loginForm);
}
