package com.yao.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.commonutils.JwtUtils;
import com.study.commonutils.ResponseResult;
import com.yao.eduorder.entity.Order;
import com.yao.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author yao
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     *
     * @param courseId
     * @param request
     * @return
     */
    @PostMapping("createOrder/{courseId}")
    public ResponseResult saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderNo = orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return ResponseResult.ok().data("orderNo", orderNo);
    }

    // 根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderNo}")
    public ResponseResult getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        return ResponseResult.ok().data("item", orderService.getOne(wrapper));
    }

}

