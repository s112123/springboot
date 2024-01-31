package com.tasty.app.module.member.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditForm {

    private String nickName;
    private String password;
    private String fileName;
    private String imageUrl;
}
