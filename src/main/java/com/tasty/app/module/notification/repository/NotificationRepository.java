package com.tasty.app.module.notification.repository;

import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.repository.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository {

    private final NotificationMapper notificationMapper;

    // 알림 등록
    public Long save(Notification notification) {
        notificationMapper.insertOne(notification);
        return notification.getNotificationId();
    }

    // 전체 알림 목록 (로그인한 계정에서 받은 Email 건수를 조회)
    public List<Notification> findByAll(String email, Pageable pageable) {
        return notificationMapper.selectAll(email, pageable);
    }

    // 읽지 않은 알림 조회
    public List<Notification> findByAllByNoRead(String email) {
        return notificationMapper.selectAllByNoRead(email);
    }

    // 알림 읽기 처리
    public void readNotification(Long notificationId) {
        notificationMapper.updateRead(notificationId);
    }

    public void readNotificationAll(List<Long> ids) {
        notificationMapper.updateReadAll(ids);
    }
}
