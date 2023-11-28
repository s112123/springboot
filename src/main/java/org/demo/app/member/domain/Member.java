package org.demo.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long memberId;
    private String email;
    private String password;
    private String username;
    private String role;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
