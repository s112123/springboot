package org.demo.app.common.interceptor.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        // 로그인 인증
        String requestURI = request.getRequestURI();
        log.info("requestURI = {}", requestURI);

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("login") == null) {
            response.sendRedirect("/login?redirectURI=" + requestURI);
            return false;
        }

        return true;
    }
}
