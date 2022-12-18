package com.study.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.study.msmservice.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @ClassName:MsmServiceImpl
 * @Auther: yao
 * @Description:
 * @Date: 2022/11/16 23:14
 * @Version: v1.0
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public Boolean send(HashMap<String, Object> map, String phone) {
        if (StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile = DefaultProfile.getProfile("default", "", "");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        // 设置相关参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setSysAction("SendSms");
        // 设置发送的相关参数
        request.putQueryParameter("PhoneNumbers", phone);
        // q签名名称
        request.putQueryParameter("SignName", "优乐选商城");// 申请阿里云，签名名称
        request.putQueryParameter("TemplateCode", "SMS_172598203");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));// 验证码数据，转换json数据

        try {
            // 最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
