/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.tc.phoenix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 支持swaggerui
 * 
 * @author min.weixm
 * @version $Id: SwaggerConfig.java, v 0.1 Dec 3, 2017 1:06:41 PM min.weixm Exp $
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.myteay.phoenix.biz.service.impl"))//过滤的接口
            .paths(PathSelectors.any()).build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("GGTC [gangutiancan.com dbcenter]--test api for dbcenter of gangutiancan.com").description("服务相关数据接口")
            .termsOfServiceUrl("http://www.gangutiancan.com/").contact(new Contact("min.weixm", "http://www.gangutiancan.com", "danlley@126.com"))
            .license("Licence Version 1.0").licenseUrl("#").version("1.0").build();
    }
}
