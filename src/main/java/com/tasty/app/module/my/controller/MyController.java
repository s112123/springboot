package com.tasty.app.module.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {

    // 내 프로필 화면
    @GetMapping("/profile")
    public String myProfile() {
        return "my/profile";
    }

    // 내가 쓴 리뷰 화면
    @GetMapping("/review")
    public String myReview() {
        return "my/review";
    }

    // 내가 찜한 리뷰 화면
    @GetMapping("/good")
    public String myGood() {
        return "my/good";
    }

    // 알림 내역 화면
    @GetMapping("/notification")
    public String myNotification() {
        return "my/notification";
    }

    // 1:1 채팅 화면
    @GetMapping("/chat")
    public String myChat() {
        return "my/chat";
    }
}
