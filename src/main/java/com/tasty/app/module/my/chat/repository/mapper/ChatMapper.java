package com.tasty.app.module.my.chat.repository.mapper;

import com.tasty.app.module.my.chat.domain.ChatMessage;
import com.tasty.app.module.my.chat.domain.ChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatMapper {

    // 채팅 방 생성
    void insertOneForChatRoom(ChatRoom chatRoom);
    
    // 채팅 방 조회
    ChatRoom selectOneForChatRoom(@Param("senderReceiver") String senderReceiver);

    // 채팅 메세지 등록
    void insertOneForChatMessage(ChatMessage message);
}
