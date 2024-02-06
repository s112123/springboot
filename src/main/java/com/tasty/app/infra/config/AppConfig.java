package com.tasty.app.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${review.upload.storage}")
    private String reviewUploadStorage;
    @Value("${member.upload.storage}")
    private String memberUploadStorage;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/review/image/**", "/members/image/**")
                .addResourceLocations(reviewUploadStorage, memberUploadStorage);
    }
}
