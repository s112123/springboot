package com.tasty.app.module.good.service;

import com.tasty.app.module.good.domain.Good;
import com.tasty.app.module.good.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodService {

    private final GoodRepository goodRepository;

    // 찜 등록 (등록된 goodId 반환)
    public Long addGood(String email, Long reviewId) {
        return goodRepository.save(Good.builder()
                .email(email)
                .reviewId(reviewId)
                .build());
    }

    // 찜 조회
    public boolean isExistsGood(String email, Long reviewId) {
        Good good = goodRepository.findByEmailAndReviewId(
                Good.builder()
                    .email(email)
                    .reviewId(reviewId)
                    .build()
        );
        return good != null;
    }

    // 찜 취소
    public Long cancelGood(String email, Long reviewId) {
        return goodRepository.delete(email, reviewId);
    }
}
