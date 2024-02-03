package com.tasty.app.module.good.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Good {

    private Long goodId;
    private String email;
    private Long reviewId;
    private LocalDateTime regDate;
}