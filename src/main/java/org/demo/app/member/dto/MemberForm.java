package org.demo.app.member.dto;

import lombok.*;
import org.demo.app.member.domain.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberForm {

    private Long memberId;

    @NotBlank(message = "email은 필수입니다")
    @Email(message = "email 형식이 아닙니다")
    private String email;

    @NotBlank(message = "password는 필수입니다")
    private String password;

    @NotBlank(message = "username은 필수입니다")
    private String username;

    private String role;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // MemberForm → Member
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
