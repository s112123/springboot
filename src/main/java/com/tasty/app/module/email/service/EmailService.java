package com.tasty.app.module.email.service;

public interface EmailService {

    // 임시 비밀번호 발송
    void sendEmailForTempPassword(String toEmail);

    // 인증 메일 발송
    void sendEmailForValid(String toEmail);
}
