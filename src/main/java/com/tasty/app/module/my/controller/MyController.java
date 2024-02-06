package com.tasty.app.module.my.controller;

import com.tasty.app.infra.dto.Pageable;
import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.service.MemberService;
import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.service.NotificationService;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.service.ReviewService;
import com.tasty.app.module.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final NotificationService notificationService;
    private final SubscribeService subscribeService;

    // 내 프로필
    @GetMapping("/profile")
    public String myProfile(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "0") int menuOption,
            Model model
    ) {
        //String email = "admin@test.com";
        // 개인정보
        Member member = memberService.getMemberByEmail(email);
        // 내가 구독한 사람
        List<Map<String, Object>> publishers = subscribeService.getPublishersFromMe(email);
        // 나를 구독한 사람
        List<Map<String, Object>> subscribers = subscribeService.getSubscribersToMeNotFromMe(email);

        // 데이터 전달
        model.addAttribute("member", member);
        model.addAttribute("publishers", publishers);
        model.addAttribute("subscribers", subscribers);
        model.addAttribute("menuOption", menuOption);
        return "/my/profile";
    }

    // 회원탈퇴
    @GetMapping("/profile/remove")
    @ResponseBody
    public String removeMembership (HttpSession session) {
        String email = (String) session.getAttribute("email");
        // DB 삭제
        memberService.removeMemberShip(email);
        // 세션 삭제
        session.removeAttribute("email");
        return "success deleted";
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

    // 알림 내역 화면
    @GetMapping("/notification")
    public String myNotification(
            @SessionAttribute("email") String email,
            @RequestParam(value = "menu_option", defaultValue = "3") int menuOption,
            Pageable pageable,
            Model model
    ) {
        // 로그인 한 사용자의 전체 알림 내역
        List<Notification> notifications = notificationService.getNotifications(email, pageable);
        // 읽지 않은 알림 개수
        long cntNoRead = notificationService.getNotificationsByNoRead(email).size();

        model.addAttribute("menuOption", menuOption);
        model.addAttribute("notifications", notifications);
        model.addAttribute("cntNoRead", cntNoRead);
        return "my/notification";
    }

    // 알림 읽음 처리
    @GetMapping("/notification/read/{notificationId}")
    @ResponseBody
    public String myNotificationRead(@PathVariable Long notificationId) {
        notificationService.readNotification(notificationId);
        return "read";
    }

    // 알림 모두 읽음 처리
    @GetMapping("/notification/read_all")
    @ResponseBody
    public String myNotificationReadAll(@SessionAttribute("email") String email) {
        List<Notification> notifications = notificationService.getNotificationsByNoRead(email);
        List<Long> ids = new ArrayList<>();
        for (Notification notification : notifications) {
            ids.add(notification.getNotificationId());
        }
        notificationService.readNotificationAll(ids);
        return "all_read";
    }
}
