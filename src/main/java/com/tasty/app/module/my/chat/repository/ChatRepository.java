package com.tasty.app.module.my.chat.repository;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import com.tasty.app.module.my.chat.repository.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRepository {

    private final ChatMapper chatMapper;

    // 채팅 방 생성
    public Long saveChatRoom(ChatRoom chatRoom) {
        chatMapper.insertOneForChatRoom(chatRoom);
        return chatRoom.getChatRoomId();
    }

    // 채팅 방 조회
    public ChatRoom findChatRoom(String senderEmail, String receiverEmail) {
        return chatMapper.selectOneForChatRoom(senderEmail, receiverEmail);
    }

    // 채팅 메세지 등록
    public Long saveChatMessage(ChatMessage message) {
        chatMapper.insertOneForChatMessage(message);
        return message.getChatId();
    }
    
    // 채팅 기록 가져오기
    public List<ChatMessage> findChatMessages(Long chatRoomId) {
        return chatMapper.selectChatMassages(chatRoomId);
    }
}
