package org.demo.app.web.member.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long memberId;
    private String email;
    private String username;
    private String password;
    private String role;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}