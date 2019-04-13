package com.hkitemplate.demo.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hkitemplate.demo.filter.ParamInterceptor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/***
 * @Desciption: 拦截器 校验token 拦截数据
 * @Date: 13:53 2018/11/25
 * @return
 */
@Configuration
@Log4j
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns("static/**","/error", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**").addPathPatterns("/**");
    }

    /**
     * 解决 swagger-ui.html 访问路径 404 问题
     *
     * @author Levin
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("========================== 静态资源加载 =========================================");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public ParamInterceptor tokenInterceptor() {
        return new ParamInterceptor();
    }

}
