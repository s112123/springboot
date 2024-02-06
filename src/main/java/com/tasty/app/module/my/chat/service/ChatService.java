package com.tasty.app.module.my.chat.service;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import com.tasty.app.module.my.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    // 채팅 방 만들기
    public void makeChatRoom(ChatRoom chatRoom) {
        // 방이 있는지 먼저 검사
        if (!existsChatRoom(chatRoom.getSenderReceiver())) {
            chatRepository.saveChatRoom(chatRoom);
        }
    }

    // 채팅방 존재하는지 검사
    public boolean existsChatRoom(String senderReceiver) {
        return chatRepository.findChatRoom(senderReceiver) != null;
    }

    // 채팅 메시지 전송
    public void sendChatMessage(String senderEmail, String receiverEmail, ChatMessage chatMessage) {
        ChatMessage message = ChatMessage.builder()
                .senderReceiver(senderEmail + "_" + chatMessage.getReceiverEmail())
                .senderEmail(senderEmail)
                .receiverEmail(receiverEmail)
                .message(chatMessage.getMessage())
                .regDate(LocalDateTime.now())
                .build();

        // 메시지 내용을 DB에 저장
        chatRepository.saveChatMessage(message);

        // 메시지 발송
        simpMessagingTemplate.convertAndSend("/public/message", message);
    }
}
