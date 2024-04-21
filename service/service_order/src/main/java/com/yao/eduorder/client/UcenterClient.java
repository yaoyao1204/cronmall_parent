package com.yao.eduorder.client;

import com.study.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("eduservice/ucenter/member/getMemberInfoById/{id}")
    UcenterMemberOrder getMemberInfoById(@PathVariable("id") String id);
}
