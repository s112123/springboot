package com.tasty.app.module.email.service;

import com.tasty.app.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final MemberService memberService;

    @Override
    public void sendEmailForTempPassword(String toEmail) {
        // 임시 6자리
        String tempPassword = (int) (Math.random() * 1000000) + "";
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // 받는사람
            message.addRecipients(Message.RecipientType.TO, toEmail);
            // 보내는 사람
            message.setFrom(new InternetAddress("v011235@naver.com", "Today's Review"));
            // 메일 제목
            message.setSubject("[Today's Review] 임시 비밀번호입니다");
            // 메일 내용
            String html = "<h3>" + tempPassword + "</h3>";
            message.setText(html, "UTF-8", "html");
            // 메일 발송
            javaMailSender.send(message);
            // 임시 비밀번호로 변경
            memberService.editPassword(toEmail, tempPassword);
        } catch (MessagingException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
