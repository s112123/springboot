package com.tasty.app.module.notification.repository.mapper;

import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.notification.domain.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {
    
    // 알림 등록
    void insertOne(Notification notification);
    
    // 전체 알림 목록
    List<Notification> selectAll(@Param("email") String email, @Param("pageable") Pageable pageable);
    
    // 읽지 않은 알림 목록
    List<Notification> selectAllByNoRead(String email);
    
    // 전체 알림 목록
    int countAllByEmail(String email);

    // 알림 읽기 처리
    void updateRead(Long notificationId);

    void updateReadAll(List<Long> ids);
}
