package com.tasty.app.infra.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    // 컨트롤러 요청 전
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();

        request.setAttribute("email", email);
        log.info("request: email={}, httpMethod={}, requestURI={}, handler={}", email, httpMethod, requestURI, handler);
        return true;
    }

    // 뷰 렌더링 후 (무조건 호출)
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    ) throws Exception {
        String email = (String) request.getAttribute("email");
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        log.info("response: email={}, httpMethod={}, requestURI={}, handler={}", email, httpMethod, requestURI, handler);
    }
}
