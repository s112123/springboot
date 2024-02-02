package com.tasty.app.module.login.service;

import com.tasty.app.module.login.form.LoginForm;

public interface LoginService {

    // 로그인 처리
    int login(LoginForm form);
}
