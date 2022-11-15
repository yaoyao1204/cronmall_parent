package com.study.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName:CrmBannerMapper
 * @Auther: yao
 * @Description: cms启动类
 * @Date: 2022/10/31 23:03
 * @Version: v1.0
 */
@SpringBootApplication
@ComponentScan({"com.study"})
@MapperScan("com.study.educms.mapper")
public class cmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(cmsApplication.class, args);
    }
}
