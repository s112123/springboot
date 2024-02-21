package com.tasty.app.infra.cookie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class CookieUtils {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    // 쿠키 유효시간: 1일
    private final int DEFAULT_MAX_AGE = 60 * 60 * 24;

    // 루트 경로에 쿠키 생성
    public void makeCookie(String name, String value) {
        makeCookie(name, value, "/", DEFAULT_MAX_AGE);
    }

    // 루트 경로에 쿠키 생성: 유효 시간 설정
    public void makeCookie(String name, String value, int day) {
        makeCookie(name, value, "/", 60 * 60 * 24 * day);
    }

    // 특정 경로에 쿠키 생성: 유효 시간 설정
    public void makeCookie(String name, String value, String path, int millisecond) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(millisecond);
        response.addCookie(cookie);
    }

    // 쿠키 값 변경
    public void updateValue(Cookie cookie, String value) {
        cookie.setValue(value);
        response.addCookie(cookie);
    }

    // 전체 쿠키 삭제
    public void removeCookies() {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
    }

    // 특정 쿠키 삭제
    public void removeCookieByName(String name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            };
        }
    }
}
