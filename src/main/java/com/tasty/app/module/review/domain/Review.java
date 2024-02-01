package com.tasty.app.module.review.domain;

import com.tasty.app.module.review.form.EditForm;
import com.tasty.app.module.review.form.ReviewForm;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
    // 썸네일 이미지 요청 주소
    private String thumbnailUrl;
    // 썸네일 이미지 파일 이름
    private String thumbnailFileName;
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
                .thumbnailUrl(reviewForm.getThumbnailUrl())
                .thumbnailFileName(reviewForm.getThumbnailFileName())
                .star(reviewForm.getStar())
                .build();
    }

    // DTO(EditForm) → Entity(Review)
    public static Review toReviewFromEditForm(EditForm form) {
        return Review.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .storeName(form.getStoreName())
                .storeAddress(form.getStoreAddress())
                .thumbnailUrl(form.getThumbnailUrl())
                .thumbnailFileName(form.getThumbnailFileName())
                .star(form.getStar())
                .build();
    }
}
