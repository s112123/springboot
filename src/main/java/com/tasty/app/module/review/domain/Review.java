package com.tasty.app.module.review.domain;

import com.tasty.app.module.review.form.ReviewForm;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    // PK
    private Long reviewId;
    // FK
    // private Long memberId;
    // 제목
    private String title;
    // 내용
    private String content;
    // 가게이름
    private String storeName;
    // 가게주소
    private String storeAddress;
    // 평점
    private Integer star;
    // 찜
    private Integer good;
    // 조회 수
    private Long hits;
    // 등록시간
    private LocalDateTime regDate;
    // 수정시간
    private LocalDateTime modDate;

    // DTO(ReviewForm) → Entity(Review)
    public static Review toReviewFromReviewForm(ReviewForm reviewForm) {
        return Review.builder()
                .title(reviewForm.getTitle())
                .content(reviewForm.getContent())
                .storeName(reviewForm.getStoreName())
                .storeAddress(reviewForm.getStoreAddress())
                .star(reviewForm.getStar())
                .build();
    }
}
