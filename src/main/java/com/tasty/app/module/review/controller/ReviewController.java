package com.tasty.app.module.review.controller;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.form.ReviewForm;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    @Value("${kakao.map.serviceKey}")
    private String serviceKey;
    private final ReviewService reviewService;

    // 리뷰 등록 화면
    @GetMapping("/add")
    public String addReviewForm() {
        return "review/add";
    }

    // 리뷰 등록 처리
    @PostMapping("/add")
    public String addReview(ReviewForm reviewForm) {
        reviewService.addReview(reviewForm);
        return "redirect:/";
    }

    // 리뷰 조회 처리
    @GetMapping("/view")
    public String viewReview(@RequestParam("review_id") Long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        model.addAttribute("serviceKey", serviceKey);
        return "review/view";
    }

    // 리뷰 편집 화면
    @GetMapping("/edit")
    public String editReview(@RequestParam("review_id") Long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "review/edit";
    }
}
