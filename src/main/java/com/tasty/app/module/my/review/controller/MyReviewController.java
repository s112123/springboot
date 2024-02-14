package com.tasty.app.module.my.review.controller;

import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.notification.service.NotificationService;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyReviewController {

    private final ReviewService reviewService;

    // 내가 쓴 리뷰 화면
    @GetMapping("/review")
    public String myReview(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "1") int menuOption,
            Pageable pageable,
            Model model
    ) {
        List<Review> reviews = reviewService.getReviewsByEmail(email, pageable);
        model.addAttribute("reviews", reviews);
        model.addAttribute("menuOption", menuOption);
        model.addAttribute("pageable", pageable);
        return "my/review";
    }

    // 내가 쓴 리뷰 화면 (리뷰 1개 삭제)
    @GetMapping("/review/remove")
    public String removeMyReview(
            @RequestParam("review_id") Long reviewId,
            @RequestParam("page") int page,
            RedirectAttributes redirectAttributes
    ) {
        reviewService.removeReview(reviewId);
        redirectAttributes.addAttribute("page", page);
        return "redirect:/my/review";
    }

    // 내가 쓴 리뷰 화면 (리뷰 선택 삭제)
    @PostMapping("/review/remove_all")
    public String removeMyReviews(
            @RequestParam(value = "checkItem", required = false) int[] checkItems,
            @RequestParam("page") int page,
            RedirectAttributes redirectAttributes
    ) {
        reviewService.removeReviews(checkItems);
        redirectAttributes.addAttribute("page", page);
        return "redirect:/my/review";
    }
}
