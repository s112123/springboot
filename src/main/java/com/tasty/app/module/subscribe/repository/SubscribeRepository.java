package com.tasty.app.module.subscribe.repository;

import com.tasty.app.module.subscribe.domain.Subscribe;
import com.tasty.app.module.subscribe.repository.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SubscribeRepository {

    private final SubscribeMapper subscribeMapper;

    // 구독하기
    public Long save(Subscribe subscribe) {
        subscribeMapper.insertOne(subscribe);
        return subscribe.getSubscribeId();
    }

    // 구독취소
    public void delete(Subscribe subscribe) {
        subscribeMapper.deleteOne(subscribe);
    }

    // 내가 구독한 사람
    public List<Map<String, Object>> findAllPublishersFromMe(String email) {
        return subscribeMapper.selectAllPublishersFromMe(email);
    }

    // 나를 구독한 사람
    public List<Map<String, Object>> findAllSubscribersToMe(String email) {
        return subscribeMapper.selectAllSubscribersToMe(email);
    }

    // 내가 구독 안했지만 나를 구독한 사람
    public List<Map<String, Object>> findAllSubscribersToMeNotFromMe(String email) {
        return subscribeMapper.selectAllSubscribersToMeNotFromMe(email);
    }

    // 구독 여부
    public boolean isExistsSubscribe(String subscriberEmail, String publisherEmail) {
        return subscribeMapper.isExistsSubscribe(subscriberEmail, publisherEmail) > 0;
    }
}
