package org.demo.app.web.login.service;

import lombok.RequiredArgsConstructor;
import org.demo.app.web.login.dto.LoginForm;
import org.demo.app.web.login.repository.LoginRepository;
import org.demo.app.web.member.domain.Member;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public boolean isLogin(LoginForm loginForm) {
        Member member = loginRepository.findMemberByEmail(loginForm.getEmail());
        return isMatchPassword(loginForm, member);
    }

    // 비밀번호 복호화 검증
    // 비밀번호가 일치하면 true, 그렇지 않으면 false
    public boolean isMatchPassword(LoginForm loginForm, Member member) {
        return BCrypt.checkpw(loginForm.getPassword(), member.getPassword());
    }
}
