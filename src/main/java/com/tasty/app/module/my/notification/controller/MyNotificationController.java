package com.tasty.app.module.my.notification.controller;

import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyNotificationController {

    private final NotificationService notificationService;

    // 알림 내역 화면
    @GetMapping("/notification")
    public String myNotification(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "3") int menuOption,
            Pageable pageable,
            Model model
    ) {
        // 로그인 한 사용자의 전체 알림 내역
        List<Notification> notifications = notificationService.getNotifications(email, pageable);
        // 읽지 않은 알림 개수
        long cntNoRead = notificationService.getNotificationsByNoRead(email).size();

        model.addAttribute("menuOption", menuOption);
        model.addAttribute("notifications", notifications);
        model.addAttribute("cntNoRead", cntNoRead);
        return "my/notification";
    }

    // 알림 읽음 처리
    @GetMapping("/notification/read/{notificationId}")
    @ResponseBody
    public String myNotificationRead(@PathVariable Long notificationId) {
        notificationService.readNotification(notificationId);
        return "read";
    }

    // 알림 모두 읽음 처리
    @GetMapping("/notification/read_all")
    @ResponseBody
    public String myNotificationReadAll(@SessionAttribute("email") String email) {
        List<Notification> notifications = notificationService.getNotificationsByNoRead(email);
        List<Long> ids = new ArrayList<>();
        for (Notification notification : notifications) {
            ids.add(notification.getNotificationId());
        }
        notificationService.readNotificationAll(ids);
        return "all_read";
    }
}
