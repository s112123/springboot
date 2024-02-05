package com.tasty.app.module.subscribe.controller;

import com.tasty.app.module.subscribe.domain.Subscribe;
import com.tasty.app.module.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/subscribes")
@RequiredArgsConstructor
public class SubscribeControllerR {

    private final SubscribeService subscribeService;

    // 구독하기
    @PostMapping
    public String subscribe(@RequestBody Subscribe subscribe) {
        subscribeService.addSubscriber(subscribe);
        return "success subscribe";
    }

    // 구독취소
    @PostMapping("/cancel")
    public String cancelSubscribe(@RequestBody Subscribe subscribe) {
        log.info("controller.subscribe={}", subscribe);
        subscribeService.cancelSubscribe(subscribe);
        return "success cancel subscribe";
    }
}
