package com.tasty.app.module.my.chat.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatRoom {

    // pk
    private Long chatRoomId;
    // 보낸 사람 (fk)
    private String senderEmail;
    // 받는 사람 (fk)
    private String receiverEmail;
    // 방 생성 날짜
    private LocalDateTime regDate;
}