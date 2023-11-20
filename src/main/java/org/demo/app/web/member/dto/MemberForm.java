package org.demo.app.web.member.dto;

import lombok.*;
import org.demo.app.web.member.domain.Member;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // 비밀번호 암호화
    public void encrypt(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 비밀번호 복호화 검증
    // 비밀번호가 일치하면 true, 그렇지 않으면 false
    public boolean decrypt(Member member, String password) {
        return BCrypt.checkpw(member.getPassword(), password);
    }
}
