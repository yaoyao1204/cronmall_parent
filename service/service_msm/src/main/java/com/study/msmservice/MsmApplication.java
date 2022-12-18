package com.study.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName:OssAplication
 * @Auther: yao
 * @Description: 启动类
 * @Date: 2022/8/21 23:48
 * @Version: v1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)// exclude = DataSourceAutoConfiguration.class 不需要加载数据库
@ComponentScan(basePackages = "com.study")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
