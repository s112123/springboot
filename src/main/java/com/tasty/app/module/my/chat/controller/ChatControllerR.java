package com.tasty.app.module.my.chat.controller;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import com.tasty.app.module.my.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatControllerR {

    private final ChatService chatService;

    // 채팅 방 생성
    @GetMapping("/make_room/{receiverEmail}")
    public String makeChatRoom(
            @SessionAttribute("email") String senderEmail,
            @PathVariable("receiverEmail") String receiverEmail
    ) {
        //String senderEmail = "admin@test.com";
        ChatRoom chatRoom = ChatRoom.builder()
                .senderEmail(senderEmail)
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
        //String senderEmail = "admin@test.com";
        chatService.sendChatMessage(senderEmail, receiverEmail, chatMessage);
    }

    // 채팅 기록 가져오기
    @GetMapping("/{receiverEmail}")
    public List<ChatMessage> getChatMessages(
            @SessionAttribute("email") String senderEmail,
            @PathVariable("receiverEmail") String receiverEmail
    ) {
        //String senderEmail = "admin@test.com";
        List<ChatMessage> chatMessages = chatService.getChatMessages(senderEmail, receiverEmail);
        return chatMessages;
    }
}
