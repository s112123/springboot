package com.tasty.app.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${naver.email.host}")
    private String naverEmailHost;
    @Value("${naver.email.username}")
    private String naverEmailUsername;
    @Value("${naver.email.password}")
    private String naverEmailPassword;
    @Value("${naver.email.port}")
    private int naverEmailPort;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(naverEmailHost);
        javaMailSender.setUsername(naverEmailUsername);
        javaMailSender.setPassword(naverEmailPassword);
        javaMailSender.setPort(naverEmailPort);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        // SMTP 프로토콜
        properties.setProperty("mail.transport.protocol", "smtp");
        // SMTP 인증
        properties.setProperty("mail.smtp.auth", "true");
        // TLS 사용
        properties.setProperty("mail.smtp.starttls.enable", "true");
        // SSL 인증
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
        // SSL 사용
        properties.setProperty("mail.smtp.ssl.enable", "true");
        return properties;
    }
}


