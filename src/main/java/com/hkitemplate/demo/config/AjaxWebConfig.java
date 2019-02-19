/**  
* <p>Title: AjaxWebConfig.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年8月29日  
* @version 1.0  
*/  
package com.hkitemplate.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhanghaow
 * <p>Title: AjaxWebConfig</p>
 *
 * <p>Description: 配置跨域请求</p>
 * <p>
 * 2018年8月29日 下午4:15:35
 */

/**
 * @author zhanghaow
 *<p>Title: AjaxWebConfig</p>
 *
 *<p>Description: 配置跨域请求</p>
 *
 * 2018年8月29日 下午4:15:35
 */

@Configuration
@EnableWebMvc
public class AjaxWebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(86400);
    }
// cors op   dele  to n t    cont
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //设置允许跨域的路径
//        registry.addMapping("/**")
//                //设置允许跨域请求的域名
//                .allowedOrigins("*")
//                //是否允许证书 不再默认开启
//                .allowCredentials(true)
//                //设置允许的方法
//                .allowedMethods("*")
//                //跨域允许时间
//                .maxAge(3600);
//    }
}