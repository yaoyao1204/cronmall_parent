package com.study.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.study.eduservice.mapper")
public class EduConfig {
    // 可以添加一个性能分析的配置类

    // 添加一个逻辑删除的插件
    @Bean
    public ISqlInjector iSqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
