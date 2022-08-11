package com.study.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果的类
 *
 *   @author yao
 *   @since 2022-08-11
 */
@Data
public class ResponseResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //构造方法私有化
    public ResponseResult() {
    }

    // 成功静态方法
    public static ResponseResult ok() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setCode(20000);
        responseResult.setMessage("成功");
        return responseResult;
    }

    // 失败静态方法
    public static ResponseResult error() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(false);
        responseResult.setCode(20001);
        responseResult.setMessage("失败");
        return responseResult;
    }

    public ResponseResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResponseResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResponseResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResponseResult data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }

    public ResponseResult data(String key,Object value) {
        this.data.put(key,value);
        return this;
    }


}
