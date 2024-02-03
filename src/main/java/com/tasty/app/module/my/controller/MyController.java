package com.tasty.app.module.my.controller;

import com.tasty.app.infra.dto.Pageable;
import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.service.MemberService;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {

    private final MemberService memberService;
    private final ReviewService reviewService;

    // 내 프로필
    @GetMapping("/profile")
    public String myProfile(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "0") int menuOption,
            Model model
    ) {
        Member member = memberService.getMemberByEmail(email);
        model.addAttribute("member", member);
        model.addAttribute("menuOption", menuOption);
        return "/my/profile";
    }

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
