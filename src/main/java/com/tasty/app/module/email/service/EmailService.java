package com.tasty.app.module.email.service;

public interface EmailService {

    // 이메일 발송: 임시 비밀번호
    void sendEmailForTempPassword(String email);
}
