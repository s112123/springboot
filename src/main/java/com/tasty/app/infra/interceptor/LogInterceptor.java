package com.tasty.app.infra.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    // 컨트롤러 요청 전
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");;
        String requestURI = request.getRequestURI();

        request.setAttribute("email", email);
        log.info("email={}, requestURI={}", email, requestURI);
        return true;
    }

    // 뷰 렌더링 후 (무조건 호출)
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    ) throws Exception {
        String requestURI = request.getRequestURI();
        String email = (String) request.getAttribute("email");
        log.info("email={}, requestURI={}", email, requestURI, ex);
    }
}
