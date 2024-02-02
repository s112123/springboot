package com.tasty.app.module.login.service;

import com.tasty.app.module.login.form.LoginForm;
import com.tasty.app.module.login.repository.LoginRepository;
import com.tasty.app.module.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public int login(LoginForm form) {
        Member member = loginRepository.login(form.getEmail());
        // 로그인 성공
        if (member != null) {
            if (form.getPassword().equals(member.getPassword())) {
                return 1;
            }
        }
        // 로그인 실패: 존재하지 않는 이메일, 비밀번호 일치 안 함
        return -1;
    }
}
