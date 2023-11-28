package org.demo.app.init.config;

import lombok.RequiredArgsConstructor;
import org.demo.app.init.service.InitService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final InitService initService;

    @PostConstruct
    public void init() {
        // 전체회원삭제
        initService.removeMembers();
        // 임시회원등록 (10명)
        initService.addMembers();
    }
}
