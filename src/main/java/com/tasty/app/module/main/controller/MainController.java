package com.tasty.app.module.main.controller;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.dto.Pageable;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ReviewService reviewService;

    // 리뷰 목록
    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "sort_option", defaultValue = "0") int sortOption,
                       Pageable pageable) {
        // sortOption: 최근 순 (0), 평점 순 (1)
        List<Review> reviews = reviewService.getReviews(sortOption, pageable);
        log.info("pageable={}", pageable);
        log.info("sortOption={}", sortOption);
        model.addAttribute("reviews", reviews);
        model.addAttribute("sortOption", sortOption);
        model.addAttribute("pageable", pageable);
        return "index";
    }
}
