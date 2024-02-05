package com.tasty.app.module.review.controller;

import com.tasty.app.module.good.service.GoodService;
import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.service.MemberService;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.form.AddForm;
import com.tasty.app.module.review.form.EditForm;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    @Value("${kakao.map.serviceKey}")
    private String serviceKey;
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final GoodService goodService;

    // 리뷰 등록 화면
    @GetMapping("/add")
    public String addReviewForm() {
        return "review/add";
    }

    // 리뷰 등록 처리
    @PostMapping("/add")
    public String addReview(@ModelAttribute("form") AddForm form, HttpSession session) {
        form.setEmail((String) session.getAttribute("email"));
        reviewService.addReview(form);
        return "redirect:/";
    }

    // 리뷰 조회 처리
    @GetMapping("/view")
    public String viewReview(
            @SessionAttribute(value = "email", required = false) String email,
            @RequestParam("review_id") Long reviewId,
            Model model
    ) {
        // 리뷰 조회
        Review review = reviewService.getReviewById(reviewId);
        // 조회 수 증가: 리뷰를 조회하는 메서드(getReviewById)가 조회 이외의 기능에도 사용되어 조회 수가 계속 올라가 별도 처리
        reviewService.increaseHits(reviewId);
        // 작성자 이미지
        Member writer = memberService.getMemberByEmail(review.getEmail());
        // 사용자 닉네임
        Member member = memberService.getMemberByEmail(email);
        // 찜 상태
        boolean isGood = goodService.isExistsGood(email, reviewId);

        model.addAttribute("review", review);
        model.addAttribute("writer", writer);
        model.addAttribute("member", member);
        model.addAttribute("isGood", isGood);
        model.addAttribute("serviceKey", serviceKey);
        return "review/view";
    }

    // 리뷰 편집 화면
    @GetMapping("/edit")
    public String editForm(@RequestParam("review_id") Long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "review/edit";
    }

    // 리뷰 변경 처리
    @PostMapping("/edit")
    public String editReview(@RequestParam("review_id") Long reviewId, EditForm form) {
        reviewService.editReview(reviewId, form);
        return "redirect:/";
    }

    // 리뷰 삭제 처리
    @GetMapping("/remove")
    public String removeReview(@RequestParam("review_id") Long reviewId) {
        reviewService.removeReview(reviewId);
        return "redirect:/";
    }
}
