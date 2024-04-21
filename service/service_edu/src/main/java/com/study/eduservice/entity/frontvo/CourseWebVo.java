package com.study.eduservice.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName:CourseWebVo
 * @Auther: yao
 * @Description:
 * @Date: 2024/4/21 23:10
 * @Version: v1.0
 */
@Data
public class CourseWebVo {
    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "课程简介")
    private String description;
    @ApiModelProperty(value = "销售数量")
    private String buyCount;
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "课程讲师姓名")
    private String teacherName;
    @ApiModelProperty(value = "课程讲师资历")
    private String intro;
    @ApiModelProperty(value = "讲师头像")
    private String avatar;
    @ApiModelProperty(value = "课程一级分类ID")
    private String subjectLevelOneId;
    @ApiModelProperty(value = "课程二级分类ID")
    private String subjectLevelTwoId;
    @ApiModelProperty(value = "一级分类名称")
    private String subjectLevelOne;
    @ApiModelProperty(value = "二级分类名称")
    private String subjectLevelTwo;

}
