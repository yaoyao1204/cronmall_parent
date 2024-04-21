package com.study.eduservice.controller.front;

import com.study.commonutils.ResponseResult;
import com.study.commonutils.ordervo.CourseWebVoOrder;
import com.study.eduservice.entity.vo.CourseInfoVo;
import com.study.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName:CourseFrontController
 * @Auther: yao
 * @Description: 课程实现类
 * @Date: 2024/4/21 23:07
 * @Version: v1.0
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Resource
    private EduCourseService courseService;
    // 根据课程id查询课程信息
    @PostMapping("getCourseInfo/{id}")
    public CourseWebVoOrder getCourseInfo(@PathVariable String id) {
        CourseInfoVo courseInfo = courseService.getCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }

}
