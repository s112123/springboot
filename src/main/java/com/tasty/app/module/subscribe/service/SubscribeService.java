package com.tasty.app.module.subscribe.service;

import com.tasty.app.module.subscribe.domain.Subscribe;
import com.tasty.app.module.subscribe.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    // 구독하기
    public void addSubscriber(Subscribe subscribe) {
        subscribeRepository.save(subscribe);
    }

    // 구독취소
    public void cancelSubscribe(Subscribe subscribe) {
        subscribeRepository.delete(subscribe);
    }

    // 내가 구독한 사람
    public List<Map<String, Object>> getPublishers(String email) {
        return subscribeRepository.findAllForPublisherByEmail(email);
    }

    // 내가 구독 안했지만 나를 구독한 사람
    public List<Map<String, Object>> getSubscribers(String email) {
        log.info("service.email={}", email);
        return subscribeRepository.findAllForSubscriberByEmail(email);
    }
}
