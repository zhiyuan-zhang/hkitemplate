/**  
* <p>Title: Swagger2Config.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年8月30日  
* @version 1.0  
*/  
package com.hkitemplate.demo.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zhanghaow
 *<p>Title: Swagger2Config</p> 
 *
 *<p>Description: </p> 
 *
 * 2018年8月30日 上午9:18:24
 */
public class Swagger2Config {
	 @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.zwsj.data.category.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("RESTful APIs For zhongwang-admin ")
	                .description("zhongwang-admin")
	                .termsOfServiceUrl("NO terms of service")
	                .contact(new Contact("", "", ""))
	                .version("1.0")
	                .build();
	    }

}
