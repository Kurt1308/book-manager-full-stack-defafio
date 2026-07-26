package com.bookmanager.backend.config.swagger;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;


@Configuration
public class PageConfig {


    public PageableHandlerMethodArgumentResolverCustomizer customize() {

        return pageableResolver -> {

            pageableResolver.setMaxPageSize(100);

        };

    }

}