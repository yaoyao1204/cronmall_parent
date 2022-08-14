package com.study.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:CrommallException
 * @Auther: yao
 * @Description: 自定义异常类
 * @Date: 2022/8/14 21:33
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CromMallException extends RuntimeException {
    private Integer code;//状态码
    private String msg;// 异常信息

    @Override
    public String toString() {
        return "CromMallException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
