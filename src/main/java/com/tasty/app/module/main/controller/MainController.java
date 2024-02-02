package com.tasty.app.module.main.controller;

import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ReviewService reviewService;

    // 리뷰 목록
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
