package com.tasty.app.infra.config;

import com.tasty.app.infra.interceptor.AuthenticateInterceptor;
import com.tasty.app.infra.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static List<String> excludePathsOfAuthentication = new ArrayList<>(Arrays.asList(
            "/css/**", "/js/**", "/upload/images/member/**", "/upload/images/review/**",
            "/", "/error", "/login", "/logout",
            "/reviews", "/review/view",
            "/member/add", "/members", "/members/send_email", "/members/exists_email", "/member/valid_email",
            "/mail/**"
    ));

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그 출력
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**");

        // 로그인 체크
        registry.addInterceptor(new AuthenticateInterceptor())
                .order(2)
                .excludePathPatterns(excludePathsOfAuthentication);
    }
}
