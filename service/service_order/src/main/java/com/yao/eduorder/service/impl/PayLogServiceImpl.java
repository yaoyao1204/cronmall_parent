package com.yao.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.study.commonutils.HttpClient;
import com.study.servicebase.exceptionhandler.CromMallException;
import com.yao.eduorder.entity.Order;
import com.yao.eduorder.entity.PayLog;
import com.yao.eduorder.mapper.PayLogMapper;
import com.yao.eduorder.service.OrderService;
import com.yao.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author yao
 * @since 2024-04-14
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;

    @Override
    public Map createNative(String orderNo) {
        // 引入微信支付相关依赖
        // 根据订单号查询订单信息
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("order_no", orderNo));
        // 使用map设置生成二维码需要参数
        Map paramMap = new HashMap();
        paramMap.put("appid", "");
        paramMap.put("mch_id", "");
        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
        paramMap.put("body", order.getCourseTitle());
        paramMap.put("out_trade_no", order.getOrderNo());// 订单号
        paramMap.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
        paramMap.put("spbill_create_ip", "127.0.0.1");
        paramMap.put("notify_url", "");
        paramMap.put("trade_type", "NATIVE");//支付类型
        paramMap.put("product_id", order.getCourseId());
        // 发送httpclient请求，传递参数xml格式，微信支付提供固定的地址
        HttpClient client = new HttpClient("");
        try {
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap, ""));// 商户的key
            client.setHttps(true);// url是https开头的
            client.post();// 执行请求发送
            // 得到调用结果
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            // 返回结果
            Map map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            return map;
        } catch (Exception e) {
            throw new CromMallException(20001, "生成二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            // 封装参数
            Map paramMap = new HashMap();
            paramMap.put("appid", "");
            paramMap.put("mch_id", "");
            paramMap.put("out_trade_no", orderNo);
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            // 发送httpclient请求，传递参数xml格式，微信支付提供固定的地址
            HttpClient client = new HttpClient("");
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap, ""));
            client.setHttps(true);

            client.post();
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            return resultMap;
        } catch (Exception e) {
            throw new CromMallException(20001, "查询支付状态失败");
        }
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) throws Exception {
        String orderNo = map.get("out_trade_no");
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("order_no", orderNo));
        if (order.getStatus() == 1) return;
        order.setStatus(1);
        orderService.updateById(order);
        // 支付表中添加支付记录
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);

    }

}
