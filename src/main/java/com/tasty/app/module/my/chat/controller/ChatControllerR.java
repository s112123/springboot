package com.tasty.app.module.my.chat.controller;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import com.tasty.app.module.my.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatControllerR {

    private final ChatService chatService;

    // 채팅 방 생성
    @GetMapping("/{receiverEmail}")
    public String makeChatRoom(
            @SessionAttribute("email") String sendEmail,
            @PathVariable("receiverEmail") String receiverEmail
    ) {
        //String sendEmail = "admin@test.com";
        ChatRoom chatRoom = ChatRoom.builder()
                .senderReceiver(sendEmail + "_" + receiverEmail)
                .senderEmail(sendEmail)
                .receiverEmail(receiverEmail)
                .build();
        chatService.makeChatRoom(chatRoom);
        return "success make chatroom";
    }

    // 메시지 전송
    @PostMapping("/{receiverEmail}")
    public void sendChatMessage(
            @SessionAttribute("email") String senderEmail,
            @PathVariable("receiverEmail") String receiverEmail,
            @RequestBody ChatMessage chatMessage
    ) {
        chatService.sendChatMessage(senderEmail, receiverEmail, chatMessage);
    }
}
