package com.tancheon.account.config;

import com.tancheon.account.interceptor.JwtAuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtAuthorizationInterceptor jwtAuthorizationInterceptor;

    private final List<String> jwtAuthorizationInterceptPatterns = null;

    private static final List<String> jwtAuthorizationExcludePatterns = Arrays.asList(
            "/assest/",
            "/account/v1/api-docs/",
            "/configuration/ui",
            "/swagger-resources/",
            "/configuration/",
            "/swagger-resources",
            "/swagger-ui.html",
            "/webjars/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthorizationInterceptor)
                .excludePathPatterns(jwtAuthorizationExcludePatterns);
    }
}
