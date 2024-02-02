package com.tasty.app.module.login.repository;

import com.tasty.app.module.member.domain.Member;

public interface LoginRepository {

    Member login(String email);
}
