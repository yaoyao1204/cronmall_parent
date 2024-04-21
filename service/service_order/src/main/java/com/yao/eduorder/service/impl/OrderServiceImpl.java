package com.yao.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.commonutils.OrderNoUtil;
import com.study.commonutils.ordervo.CourseWebVoOrder;
import com.study.commonutils.ordervo.UcenterMemberOrder;
import com.yao.eduorder.client.EduClient;
import com.yao.eduorder.client.UcenterClient;
import com.yao.eduorder.entity.Order;
import com.yao.eduorder.mapper.OrderMapper;
import com.yao.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author yao
 * @since 2024-04-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        // 通过远程调用获取用户id获取用户信息
        UcenterMemberOrder memberOrder = ucenterClient.getMemberInfoById(memberId);
        // 通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfo = eduClient.getCourseInfo(courseId);
        // 创建Order对象，向对象里面设置数据
        Order order = new Order();
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfo.getTitle());
        order.setCourseCover(courseInfo.getCover());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setTotalFee(courseInfo.getPrice());
        order.setMemberId(memberId);
        order.setNickname(memberOrder.getNickname());
        order.setMobile(memberOrder.getMobile());
        order.setStatus(0);
        order.setPayType(1);
        order.setOrderNo(OrderNoUtil.getOrderNo());
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
