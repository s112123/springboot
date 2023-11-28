package org.demo.app.member.dto;

import lombok.*;
import org.demo.app.member.domain.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberForm {

    private Long memberId;
    private String email;
    private String password;
    private String username;
    private String role;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // this → Member
    public Member toMember() {
        return Member.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .username(username)
                .role(role)
                .regDate(regDate)
                .modDate(modDate)
                .build();
    }
}
