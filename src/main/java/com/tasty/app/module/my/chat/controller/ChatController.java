package com.tasty.app.module.my.chat.controller;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.service.ChatService;
import com.tasty.app.module.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class ChatController {

    private final SubscribeService subscribeService;
    private final ChatService chatService;

    // 1:1 채팅 화면
    @GetMapping("/chat")
    public String myChat(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "4") int menuOption,
            Model model
    ) {
        //String email = "admin@test.com";
        List<Map<String, Object>> publishers = subscribeService.getPublishersFromMe(email);
        // 임시결과
/*        for (Map<String, Object> publisher : publishers) {
            log.info("publisher={}", publisher);
        }*/

        model.addAttribute("publishers", publishers);
        model.addAttribute("menuOption", menuOption);
        return "my/chat";
    }

    // 메세지 발송
    @MessageMapping("/chat_service")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatService.sendChatMessage(chatMessage);
        return chatMessage;
    }
}
