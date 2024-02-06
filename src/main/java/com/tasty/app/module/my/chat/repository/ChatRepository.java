package com.tasty.app.module.my.chat.repository;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import com.tasty.app.module.my.chat.repository.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRepository {

    private final ChatMapper chatMapper;

    // 채팅 방 생성
    public String saveChatRoom(ChatRoom chatRoom) {
        chatMapper.insertOneForChatRoom(chatRoom);
        return chatRoom.getSenderReceiver();
    }

    // 채팅 방 조회
    public ChatRoom findChatRoom(String senderReceiver) {
        return chatMapper.selectOneForChatRoom(senderReceiver);
    }

    // 채팅 메세지 등록
    public Long saveChatMessage(ChatMessage message) {
        chatMapper.insertOneForChatMessage(message);
        return message.getChatId();
    }
}