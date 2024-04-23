package com.yao.eduorder.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.study.commonutils.ResponseResult;
import com.yao.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author yao
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/eduorder/pay-log")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;
    // 生成微信生成二维码接口
    @RequestMapping("createNative/{orderNo}")
    public ResponseResult createNative(@PathVariable String orderNo) {
        Map map=payLogService.createNative(orderNo);
        return ResponseResult.ok().data(map);
    }
    @RequestMapping("queryPayStatus/{orderNo}")
    public ResponseResult queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {
            return ResponseResult.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrderStatus(map);
            return ResponseResult.ok().message("支付成功");
        }
        return ResponseResult.ok().message("支付中");
    }

}

