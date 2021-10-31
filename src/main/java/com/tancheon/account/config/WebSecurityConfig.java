package com.tancheon.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity webSecurity) {

        // SWAGGER IGNORE
        webSecurity.ignoring().antMatchers(
                 "/account/v1/api-docs/**",
                "/assest/**", "/configuration/ui", "/configuration/**", "/webjars/**",
                "/swagger-resources/**", "/swagger-resources", "/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/account/v2/**").hasAnyRole("")
                .anyRequest().authenticated()
                .and()
//                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
        //TODO: Jwt 검증 필터 추가
        ;
    }
}