package com.tasty.app.module.my.profile.controller;

import com.tasty.app.module.member.domain.Member;
import com.tasty.app.module.member.service.MemberService;
import com.tasty.app.module.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class ProfileController {

    private final MemberService memberService;
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
}
