package com.study.educenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName:WxApiController
 * @Auther: yao
 * @Description: 微信生成二维码
 * @Date: 2023/11/6 21:04
 * @Version: v1.0
 */
@CrossOrigin
@Controller  // 只请求地址，不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    // 1.生成微信二维码
    @GetMapping("login")
    public String getWxCode() {
        // 固定地址，后面拼接参数

        String baseUrl = "http://open.weixin.qq.com/connect/qrconnect?appid=%s" +
                "&redirect_uri=%s" + "&response_type=code" + "&scope=snsapi_login" + "&state=%s" + "#wechat_redirect";
        // 对redirect_url进行URLEncoder编码
        String redirectUrl = "";
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format(baseUrl, "", redirectUrl, "atguigu");
        // 重定向到请求微信地址里面
        return "redirect:" + url;
    }

    // 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        // 1.获取code值，临时票据，类似于验证码
        // 2.拿着code请求微信固定的地址，得到两个值access_token和openid
        String baseAccessTokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token"+
                "?appid=%s"+
                "&secret=%s"+
                "&code=%s"+
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl, "微信的appid", "微信的秘钥", code);
        // 请求拼接好的地址，得到返回的两个值 access_token和openid
        // 使用HTTPClient发送请求，得到返回结果

        return "redirect:http://localhost:3000";
    }

}
