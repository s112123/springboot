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
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final MemberService memberService;
    public static Map<String, String> validEmailIds = new ConcurrentHashMap<>();

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
            String html = "";
            html += "<div style=\"width:350px; margin:50px 0px; padding:20px; border:1px solid rgb(210, 210, 210); " +
                    "             border-radius:5px;\">";
            html += "  <p style=\"margin-bottom:25px; font-size:10px; font-weight:bold;\">Today's Review</p>";
            html += "  <p style=\"padding:15px 0px; font-weight:bold; border-bottom:1px solid rgb(210, 210, 210);\">";
            html += "    [임시 비밀번호 발송]</p>";
            html += "  <p style=\"padding:40px 0px; font-size:24px; font-weight:bold; text-align:center;\">" +
                         tempPassword + "</p>";
            html += "  <p style=\"margin-bottom:25px; font-size:14px; text-align:center;\">";
            html += "    로그인 후, 비밀번호를 변경하시길 바랍니다</p>";
            html += "</div>";
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

    @Override
    public void sendEmailForValid(String toEmail) {
        validEmailIds.put(toEmail, UUID.randomUUID().toString());
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // 받는사람
            message.addRecipients(Message.RecipientType.TO, toEmail);
            // 보내는 사람
            message.setFrom(new InternetAddress("v011235@naver.com", "Today's Review"));
            // 메일 제목
            message.setSubject("[Today's Review] 인증 메일 발송");
            // 메일 내용
            String html = "<a href=\"http://localhost:8080/member/valid_email?email=" + toEmail +
                          "&valid_id=" + validEmailIds.get(toEmail) + "\">인증하기</a>";
            message.setText(html, "UTF-8", "html");
            // 메일 발송
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
