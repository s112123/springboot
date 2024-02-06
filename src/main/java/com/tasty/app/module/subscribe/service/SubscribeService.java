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
    public List<Map<String, Object>> getPublishersFromMe(String email) {
        return subscribeRepository.findAllPublishersFromMe(email);
    }

    // 나를 구독한 사람
    public List<Map<String, Object>> getSubscribersToMe(String email) {
        return subscribeRepository.findAllSubscribersToMe(email);
    }

    // 내가 구독 안했지만 나를 구독한 사람
    public List<Map<String, Object>> getSubscribersToMeNotFromMe(String email) {
        return subscribeRepository.findAllSubscribersToMeNotFromMe(email);
    }

    // 구독 여부
    public boolean isSubscribed(String subscriberEmail, String publisherEmail) {
        return subscribeRepository.isExistsSubscribe(subscriberEmail, publisherEmail);
    }
}
