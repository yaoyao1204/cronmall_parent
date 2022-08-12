package com.study.servicebase.exceptionhandler;

import com.study.commonutils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName:GlobalExceptionHandler
 * @Auther: YooAo
 * @Description: 全局异常处理类
 * @Date: 2022/8/13 00:24
 * @Version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // 指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody // 为了返回数据
    public ResponseResult error(Exception e){
        e.printStackTrace();
        return ResponseResult.error().message("执行了全局异常处理。。");
    }
}
