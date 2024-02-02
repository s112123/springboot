package com.tasty.app.module.member.domain;

import com.tasty.app.module.member.form.AddForm;
import com.tasty.app.module.member.form.EditForm;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {

    // pk
    private Long memberId;
    // 이메일 (아이디)
    private String email;
    // 비밀번호
    private String password;
    // 별명
    private String nickName;
    // 프로필 이미지 파일명
    private String fileName;
    // 프로필 이미지 파일 경로
    private String imageUrl;
    // 등록일
    private LocalDateTime regDate;
    // 수정일
    private LocalDateTime modDate;

    // DTO(MemberForm) → Entity(Member)
    public static Member toMemberFromAddForm(AddForm addForm) {
        return Member.builder()
                .email(addForm.getEmail())
                .password(addForm.getPassword())
                .nickName(addForm.getEmail())
                .imageUrl("/members/image/default_profile_image.png")
                .fileName("default_profile_image.png")
                .build();
    }

    // DTO(MemberEditForm) → Entity(Member)
    public static Member toMemberFromEditForm(EditForm form) {
        return Member.builder()
                .nickName(form.getNickName())
                .password(form.getPassword())
                .fileName(form.getFileName())
                .imageUrl(form.getImageUrl())
                .build();
    }
}
