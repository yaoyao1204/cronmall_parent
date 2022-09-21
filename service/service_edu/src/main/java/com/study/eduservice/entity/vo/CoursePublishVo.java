package com.study.eduservice.entity.vo;

import lombok.Data;

/**
 * @ClassName:CoursePublishVo
 * @Auther: yao
 * @Description: 课程发布实体类
 * @Date: 2022/9/12 23:29
 * @Version: v1.0
 */

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private String lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;// 只用于显示
}
