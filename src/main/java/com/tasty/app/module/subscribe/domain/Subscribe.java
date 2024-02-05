package com.tasty.app.module.subscribe.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subscribe {

    // pk
    private Long subscribeId;
    // 구독자 (fk)
    private String subscriberEmail;
    // 발행자 (fk)
    private String publisherEmail;
}