/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * 发布前变更日志路径：/etc/myteay/plateform/logs/customer
 * 
 * 测试接口地址：http://192.168.56.102:40009/swagger-ui.html
 * 
 * 容器启动指令：docker run -p 40009:40009 -v /home/danlley/docker/share/logs/customer:/etc/myteay/plateform/logs/customer -v /home/danlley/docker/share/upload/qrcode/usercode:/etc/myteay/plateform/qrcode/usercode customer_201712031740
 * 
 * @author min.weixm
 * @version $Id: Application.java, v 0.1 Nov 19, 2017 4:09:56 PM min.weixm Exp $
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
@MapperScan("com.myteay.phoenix.common.persist")
@ImportResource("classpath*:/META-INF/spring/tc-*.xml")
public class Application {

    /**
     * 接口访问地址： http://localhost:40001/swagger-ui.html
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
