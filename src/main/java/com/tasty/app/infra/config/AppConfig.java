package com.tasty.app.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Slf4j
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${upload.storage.member}")
    private String uploadStorageMember;
    @Value("${upload.storage.review}")
    private String uploadStorageReview;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info(uploadStorageMember);
        log.info(uploadStorageReview);
        registry.addResourceHandler("/images/member/**", "/images/review/**")
                .addResourceLocations(uploadStorageMember, uploadStorageReview);
    }
}
