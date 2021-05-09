package com.xiya.demo.config;

import com.xiya.demo.controller.interceptor.ClientInfoInterceptor;
import com.xiya.demo.controller.interceptor.UserTokenAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用户鉴权 token 拦截器
 *
 * @author N3verL4nd
 * @date 2020/3/25
 */
@Configuration
public class TokenInterceptorConfigure implements WebMvcConfigurer {


    @Bean
    public UserTokenAuthInterceptor userTokenAuthInterceptor() {
        return new UserTokenAuthInterceptor();
    }

    @Bean
    public ClientInfoInterceptor clientInfoInterceptor() {
        return new ClientInfoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenAuthInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(clientInfoInterceptor()).addPathPatterns("/**");

    }
}