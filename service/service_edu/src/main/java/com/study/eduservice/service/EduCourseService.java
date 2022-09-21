package com.study.eduservice.service;

import com.study.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.eduservice.entity.vo.CourseInfoVo;
import com.study.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);
}
