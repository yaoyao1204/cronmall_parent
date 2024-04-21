package com.yao.eduorder.service;

import com.yao.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author yao
 * @since 2024-04-14
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberIdByJwtToken);
}
