package com.study.eduservice.controller;


import com.study.commonutils.ResponseResult;
import com.study.eduservice.entity.EduCourse;
import com.study.eduservice.entity.vo.CourseInfoVo;
import com.study.eduservice.entity.vo.CoursePublishVo;
import com.study.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api("课程管理")
public class EduCourseController {
    @Resource
    private EduCourseService courseService;

    // 添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    @ApiOperation("添加课程")
    public ResponseResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return ResponseResult.ok().data("courseId", id);
    }

    // 根据课程id查询课程基本信息
    @GetMapping("/getCourseInfo/{courseId}")
    @ApiOperation("查询课程基本信息")
    public ResponseResult getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return ResponseResult.ok().data("courseInfoVo", courseInfoVo);
    }

    // 修改课程信息
    @PostMapping("updateCourseInfo")
    public ResponseResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return ResponseResult.ok();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public ResponseResult getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return ResponseResult.ok().data("publishCourse", coursePublishVo);
    }

    // 课程最终发布
    // 修改课程状态
    @PostMapping("publishCourse/{id}")
    public ResponseResult publishCourse(@PathVariable String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");// 设置课程发布的状态
        courseService.updateById(course);
        return ResponseResult.ok();
    }

}

