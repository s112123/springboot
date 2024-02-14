package com.tasty.app.module.my.good.controller;

import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class GoodController {

    private final ReviewService reviewService;

    // 내가 찜한 리뷰 화면
    @GetMapping("/good")
    public String myGood(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "2") int menuOption,
            Pageable pageable,
            Model model
    ) {
        // 내가 찜한 리뷰 목록
        List<Map<String, Object>> reviews = reviewService.getReviewsByGoodByEmail(email, pageable);
        log.info("reviews={}", reviews);

        model.addAttribute("menuOption", menuOption);
        model.addAttribute("reviews", reviews);
        return "my/good";
    }
}
