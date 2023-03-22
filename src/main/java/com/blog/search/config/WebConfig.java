package com.blog.search.config;

import com.blog.search.converter.StringToKakaoBlogSearchSortConverter;
import com.blog.search.converter.StringToNaverBlogSearchSortConverter;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/**
 * Web Mvc 설정 관련 Configuration
 *
 * 해당 클래스는 다음과 같은 설정 정보를 Bean으로 등록한다.
 *
 * - Converter
 * - methodValidationPostProcessor
 * - restTemplate
 * - serviceLocatorOpenApiInfoLocator
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToKakaoBlogSearchSortConverter());
        registry.addConverter(new StringToNaverBlogSearchSortConverter());
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public ServiceLocatorFactoryBean serviceLocatorOpenApiInfoLocator() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(OpenApiInfoLocator.class);
        return factoryBean;
    }
}
