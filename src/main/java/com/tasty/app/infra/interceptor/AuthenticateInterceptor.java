package com.tasty.app.infra.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AuthenticateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
