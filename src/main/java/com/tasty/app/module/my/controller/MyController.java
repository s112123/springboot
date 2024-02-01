package com.tasty.app.module.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/my")
public class MyController {

    // 내가 쓴 리뷰 화면
    @GetMapping("/review")
    public String myReview(
            @RequestParam(value = "menu_option", defaultValue = "1") int menuOption,
            Model model
    ) {
        model.addAttribute("menuOption", menuOption);
        return "my/review";
    }

    // 내가 찜한 리뷰 화면
    @GetMapping("/good")
    public String myGood(
            @RequestParam(value = "menu_option", defaultValue = "2") int menuOption,
            Model model
    ) {
        model.addAttribute("menuOption", menuOption);
        return "my/good";
    }

    // 알림 내역 화면
    @GetMapping("/notification")
    public String myNotification(
            @RequestParam(value = "menu_option", defaultValue = "3") int menuOption,
            Model model
    ) {
        model.addAttribute("menuOption", menuOption);
        return "my/notification";
    }

    // 1:1 채팅 화면
    @GetMapping("/chat")
    public String myChat(
            @RequestParam(value = "menu_option", defaultValue = "4") int menuOption,
            Model model
    ) {
        model.addAttribute("menuOption", menuOption);
        return "my/chat";
    }
}
