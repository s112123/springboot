package com.tasty.app.module.notification.controller;

import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationControllerR {

    private final NotificationService notificationService;

    // 알림 구독
    @GetMapping(value = "/subscribe/{email}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable("email") String email) {
        SseEmitter emitter = notificationService.subscribe(email);
        return emitter;
    }

    // 서버 푸시
    @PostMapping(value = "/publish/{toEmail}")
    public void publish(@PathVariable("toEmail") String toEmail, @RequestBody Notification notification) {
        // 알림 발송
        notificationService.sendNotification(toEmail, notification);
        // 알림 저장
        notificationService.addNotification(notification);
    }
}
