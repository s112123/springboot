package com.tasty.app.module.my.chat.repository.mapper;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    // 채팅 방 생성
    void insertOneForChatRoom(ChatRoom chatRoom);
    
    // 채팅 방 조회
    ChatRoom selectOneForChatRoom(
            @Param("senderEmail") String senderEmail, @Param("receiverEmail") String receiverEmail
    );

    // 채팅 메세지 등록
    void insertOneForChatMessage(ChatMessage message);

    // 채팅 기록 가져오기
    List<ChatMessage> selectChatMassages(Long chatRoomId);
}
