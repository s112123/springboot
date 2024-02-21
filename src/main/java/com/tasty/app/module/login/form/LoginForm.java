package com.tasty.app.module.login.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginForm {

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식이 아닙니다 (예: email@email.com)")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

    private boolean remember;
}
