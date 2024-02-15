package com.tasty.app.module.review.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReviewImage {

    private Long reviewImageId;
    private Long reviewId;
    private String uploadFileName;
}
