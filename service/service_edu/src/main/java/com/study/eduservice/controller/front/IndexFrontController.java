package com.study.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.commonutils.ResponseResult;
import com.study.eduservice.entity.EduCourse;
import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.service.EduCourseService;
import com.study.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName:IndexFrontController
 * @Auther: yao
 * @Description: 轮播图
 * @Date: 2022/10/31 23:44
 * @Version: v1.0
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    // 查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public ResponseResult index() {
        // 查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> list = courseService.list(wrapper);
        // 查询前4位名师
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);
        return ResponseResult.ok().data("eduList", list).data("teacherList", teacherList);
    }


}
