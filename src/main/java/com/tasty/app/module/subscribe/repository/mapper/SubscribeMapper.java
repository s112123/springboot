package com.tasty.app.module.subscribe.repository.mapper;

import com.tasty.app.module.subscribe.domain.Subscribe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubscribeMapper {

    // 구독하기
    void insertOne(Subscribe subscribe);

    // 구독취소
    void deleteOne(Subscribe subscribe);

    // 내가 구독한 사람
    List<Map<String, Object>> selectAllForPublisherByEmail(String email);

    // 내가 구독 안했지만 나를 구독한 사람
    List<Map<String, Object>> selectAllForSubscriberByEmail(String email);
}
