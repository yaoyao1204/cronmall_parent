package com.study.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.study.eduservice.entity.EduCourse;
import com.study.eduservice.entity.EduCourseDescription;
import com.study.eduservice.entity.vo.CourseInfoVo;
import com.study.eduservice.entity.vo.CoursePublishVo;
import com.study.eduservice.mapper.EduCourseMapper;
import com.study.eduservice.service.EduChapterService;
import com.study.eduservice.service.EduCourseDescriptionService;
import com.study.eduservice.service.EduCourseService;
import com.study.eduservice.service.EduVideoService;
import com.study.servicebase.exceptionhandler.CromMallException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService courseDescriptionService;
    @Resource
    private EduVideoService videoService;
    @Resource
    private EduChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 1. 向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        if (Strings.isNullOrEmpty(eduCourse.getSubjectParentId())) {
            eduCourse.setSubjectParentId("");
        }
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new CromMallException(20001, "添加课程信息失败");
        }
        // 获取添加之后的课程id
        String id = eduCourse.getId();

        // 2.向课程简介表添加课程简介
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoVo.getDescription());
        description.setId(id);
        courseDescriptionService.save(description);
        return id;

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        // 2 查询课程描述表
        EduCourseDescription description = courseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        courseInfoVo.setDescription(description.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0) {
            throw new CromMallException(20001, "修改课程信息失败！");
        }
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        boolean b = courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public void removeCourse(String courseId) {
        // 删除小节信息
        videoService.removeVideoByCourseId(courseId);
        // 删除章节信息
        chapterService.removeChapterService(courseId);
        // 删除描述
        courseDescriptionService.removeById(courseId);
        // 删除课程信息
        int i = baseMapper.deleteById(courseId);
        if (i == 0) {
            throw new CromMallException(20001, "删除失败");
        }

    }

}
