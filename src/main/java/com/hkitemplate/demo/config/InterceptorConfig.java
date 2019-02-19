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

import java.util.List;

/***
 * @Desciption: 拦截器 校验token 拦截数据
 * @Date: 13:53 2018/11/25
 * @return
 */
@Configuration
@Log4j
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns("/swagger**/**", "/webjars**/**", "/userinfo/user/login", "/userinfo/root", "/userinfo/message").addPathPatterns("/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public ParamInterceptor tokenInterceptor() {
        return new ParamInterceptor();
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        log.info("configureMessageConverters");

        converters.add(fastJsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {

        log.info("fastJsonHttpMessageConverter");

        return new FastJsonHttpMessageConverter();
    }


}
