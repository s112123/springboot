package com.tasty.app.module.review.controller;

import com.tasty.app.module.review.form.ReviewForm;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        ReviewForm reviewForm = reviewService.getReviewById(reviewId);
        model.addAttribute("reviewForm", reviewForm);
        return "review/view";
    }

    // 리뷰 편집 화면
    @GetMapping("/edit")
    public String editReview(@RequestParam("review_id") Long reviewId, Model model) {
        ReviewForm reviewForm = reviewService.getReviewById(reviewId);
        model.addAttribute("reviewForm", reviewForm);
        return "review/edit";
    }
}
