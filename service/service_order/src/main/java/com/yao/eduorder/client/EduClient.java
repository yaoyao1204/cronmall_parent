package com.yao.eduorder.client;

import com.study.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface EduClient {
    @GetMapping("eduservice/coursefront/getCourseInfo/{id}")
    CourseWebVoOrder getCourseInfo(@PathVariable("id") String id);
}
