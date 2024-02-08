package com.tasty.app.module.my.chat.controller;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import com.tasty.app.module.my.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
                .chatRoomKey(UUID.randomUUID().toString())
                .senderEmail(senderEmail)
                .receiverEmail(receiverEmail)
                .build();

        // 채팅 방 Key 반환
        return chatService.makeChatRoom(chatRoom);
    }

    // 채팅 기록 가져오기
    @GetMapping("/{receiverEmail}")
    public List<ChatMessage> getChatMessages(
            @SessionAttribute("email") String senderEmail,
            @PathVariable("receiverEmail") String receiverEmail
    ) {
        //String senderEmail = "admin@test.com";
        //log.info("receiverEmail={}", receiverEmail);
        List<ChatMessage> chatMessages = chatService.getChatMessages(senderEmail, receiverEmail);
        return chatMessages;
    }

    // 읽음 처리
    @PatchMapping("/read")
    public String readMessage(@RequestBody ChatMessage chatMessage) {
        chatService.readMessage(chatMessage);
        return "읽음";
    }
}
