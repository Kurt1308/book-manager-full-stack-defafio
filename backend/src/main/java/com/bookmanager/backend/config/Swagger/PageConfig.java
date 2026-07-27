package com.bookmanager.backend.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;



@Configuration
public class PageConfig {


    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {


        return pageableResolver -> {


            pageableResolver.setMaxPageSize(100);


        };

    }

}