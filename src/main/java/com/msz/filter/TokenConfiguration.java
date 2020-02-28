package com.msz.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Description:
 * @author: cww
 * @date: 2019/7/17 20:51
 */
@Configuration
public class TokenConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
        // 这个方法才能在拦截器中自动注入查询数据库的对象
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry ){
        InterceptorRegistration ir = registry.addInterceptor(tokenInterceptor());
        ir.excludePathPatterns("/swagger-resources/**");
        ir.excludePathPatterns("/v2/**");
        /*ir.addPathPatterns("/admin/**");*/
        //配置生成器：添加一个拦截器，拦截路径为API以后的路径
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

}
