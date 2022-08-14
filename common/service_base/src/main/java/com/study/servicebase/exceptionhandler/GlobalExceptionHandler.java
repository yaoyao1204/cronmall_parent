package com.study.servicebase.exceptionhandler;

import com.study.commonutils.ExceptionUtil;
import com.study.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName:GlobalExceptionHandler
 * @Auther: yao
 * @Description: 全局异常处理类
 * @Date: 2022/8/13 00:24
 * @Version: v1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody // 为了返回数据
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        return ResponseResult.error().message("执行了全局异常处理。。");
    }

    // 特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseResult erro(ArithmeticException e) {
        e.printStackTrace();
        return ResponseResult.error().message("执行了ArithmeticException异常处理。。。");
    }

    // 创建自定义异常类继承RuntimeException 写异常属性
    @ExceptionHandler(CromMallException.class)
    @ResponseBody
    public ResponseResult error(CromMallException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return ResponseResult.error().code(e.getCode()).message(e.getMsg());
    }

}
