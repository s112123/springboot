package com.tasty.app.module.notification.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Notification {

    // pk
    private Long notificationId;
    // 알림을 보내는 계정: 로그인한 계정
    private String fromEmail;
    // 알림을 받는 계정 (fk): 예) 리뷰 작성자
    private String toEmail;
    // 알림 종류: GOOD (찜), SUBSCRIBE (구독), CHAT (1:1 채팅)
    private String category;
    // 알림 내용
    private String content;
    // 관련 URL
    private String url;
    // 읽음 유무
    private Boolean isRead;
    // 등록일
    private LocalDateTime regDate;
}
