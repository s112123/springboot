package com.tasty.app.module.good.controller;

import com.tasty.app.module.good.domain.Good;
import com.tasty.app.module.good.service.GoodService;
import com.tasty.app.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodControllerR {

    private final GoodService goodService;

    // 찜 등록
    @GetMapping("/{reviewId}")
    public Map<String, Object> addGood(
            @SessionAttribute(value = "email", required = false) String email,
            @PathVariable("reviewId") Long reviewId
    ) {
        Map<String, Object> response = new HashMap<>();

        // 로그인이 안되어 있는 경우
        if (email == null) {
            response.put("message", 0);
            return response;
        }
        
        // 찜 등록
        long res = goodService.addGood(email, reviewId);
        response.put("message", res);

        return response;
    }

    // 찜 취소
    @GetMapping("/cancel/{reviewId}")
    public Map<String, Object> cancelGood(@PathVariable("reviewId") Long reviewId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        // 로그인시, 세션에 저장된 email 값 가져옴
        String email = (String) session.getAttribute("email");

        // 로그인이 안되어 있는 경우
        if (email == null) {
            response.put("message", 0);
            return response;
        }

        // 찜 등록 성공시, 등록된 행 수 반환
        long res = goodService.cancelGood(email, reviewId);
        response.put("message", res);

        return response;
    }
}
