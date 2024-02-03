package com.tasty.app.module.member.form;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddForm {

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식이 아닙니다 (예: email@email.com)")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;
}