package org.demo.app.web.member.dto;

import lombok.*;
import org.demo.app.web.member.domain.Member;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberForm {

    private Long memberId;
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식이 아닙니다")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이름을 입력하세요")
    private String username;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // 비밀번호 암호화
    public void encrypt(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
