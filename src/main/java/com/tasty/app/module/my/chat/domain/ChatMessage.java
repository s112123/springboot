package com.tasty.app.module.my.chat.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessage {

    // pk
    private Long chatId;
    // 채팅방 ID (fk)
    private Long chatRoomId;
    // 보낸 사람 (fk)
    private String senderEmail;
    // 받는 사람 (fk)
    private String receiverEmail;
    // 메세지
    private String message;
    // 읽음 유무
    private Boolean isRead;
    // 메세지 발송 시간
    private LocalDateTime regDate;
}
