package com.study.msmservice.controller;

import com.study.commonutils.ResponseResult;
import com.study.msmservice.service.MsmService;
import com.study.msmservice.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:MsmController
 * @Auther: yao
 * @Description: 整合短信
 * @Date: 2022/11/16 23:11
 * @Version: v1.0
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public ResponseResult sendMsm(@PathVariable String phone) {
        // 从redis获取验证码，如果获取到直接返回
        String s = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(s)) return ResponseResult.ok();

        // 生成随机值，传递阿里云进行发送
        String code = RandomUtil.getFourBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        Boolean isSend = msmService.send(map, phone);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return ResponseResult.ok();
        } else {
            return ResponseResult.error().message("短信发送失败。");
        }
    }

}
