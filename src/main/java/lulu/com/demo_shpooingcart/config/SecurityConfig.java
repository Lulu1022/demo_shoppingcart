//package lulu.com.demo_shpooingcart.config;// 使用 Spring Security
//package com.lulu.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().and()  // 啟用 CORS 支持
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll();  // 這裡可以根據需要配置具體的授權策略
//
//        return http.build();
//    }
//}

