package com.tasty.app.module.notification.service;

import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.repository.NotificationRepository;
import com.tasty.app.module.notification.repository.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    // SseEmitter 저장소
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Long TIME_OUT = 1000L * 60L * 1L;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    // SseEmitter 생성 (구독)
    public SseEmitter subscribe(String email) {
        SseEmitter emitter = new SseEmitter(TIME_OUT);
        emitters.put(email, emitter);

        emitter.onCompletion(() -> emitters.remove(email));
        emitter.onTimeout(() -> emitters.remove(email));

        // 최초 구독시, Dummy 데이터 전송
        sendNotification(email, "subscribe");

        // 읽지 않은 알림이 있으면 알림 전송
        List<Notification> notifications = getNotificationsByNoRead(email);
        if (!notifications.isEmpty()) {
            sendNotification(email, notifications);
        }

        return emitter;
    }

    // 알림 보내기
    public void sendNotification(String email, Object notifications) {
        log.info("알림 보내기");
        SseEmitter emitter = emitters.get(email);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().id(email).name("sse").data(notifications));
            } catch (IOException e) {
                emitters.remove(email);
            }
        }
    }

    // 알림 등록
    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    // 전체 알림 목록
    public List<Notification> getNotifications(String email, Pageable pageable) {
        int total = notificationMapper.countAllByEmail(email);
        pageable.setTotal(total);
        return notificationRepository.findByAll(email, pageable);
    }

    // 읽지 않은 알림 목록
    public List<Notification> getNotificationsByNoRead(String email) {
        return notificationRepository.findByAllByNoRead(email);
    }

    // 알림 읽기
    public void readNotification(Long notificationId) {
        notificationRepository.readNotification(notificationId);
    }

    // 알림 모두 읽기
    public void readNotificationAll(List<Long> ids) {
        notificationRepository.readNotificationAll(ids);
    }
}
