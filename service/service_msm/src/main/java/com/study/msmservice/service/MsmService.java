package com.study.msmservice.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName:MsmService
 * @Auther: yao
 * @Description:
 * @Date: 2022/11/16 23:13
 * @Version: v1.0
 */
@Component
public interface MsmService {
    Boolean send(HashMap<String, Object> map, String phone);
}
