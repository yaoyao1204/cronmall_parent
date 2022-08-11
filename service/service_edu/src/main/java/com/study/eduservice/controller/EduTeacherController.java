package com.study.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.commonutils.ResponseResult;
import com.study.eduservice.controller.vo.TeacherQuery;
import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yao
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(description = "讲师管理")
public class EduTeacherController {
    // 把service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    // 1.查询讲师表中所有数据  rest风格
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public ResponseResult findAllTeacher() {
        // 调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);
        return ResponseResult.ok().data("items", list);
    }

    // 2.讲师逻辑删除功能  1)添加逻辑删除的配置 2）删除的实体类字段加注解@TableLogic 3）写controller的方法
    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除讲师")
    public ResponseResult removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error();
        }
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation("分页查询")
    public ResponseResult pageListTeacher(@PathVariable long current,
                                          @PathVariable long limit) {
        // 创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        eduTeacherService.page(eduTeacherPage, null);
        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        long total = eduTeacherPage.getTotal();// 总记录数
        List<EduTeacher> records = eduTeacherPage.getRecords();// 数据list集合

        return ResponseResult.ok().data("total", total).data("records", records);
    }

    // 4.条件查询带分页的方法
    @GetMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation("带条件的分页查询")
    public ResponseResult pageTeacherCondition(@PathVariable long current,
                                               @PathVariable long limit,
                                               TeacherQuery teacherQuery) {
        // 创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis 学过动态sql
        // 判断条件值是否为空，不为空则拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (level != null) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.gt("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.lt("gmt_create", end);
        }

        eduTeacherService.page(teacherPage, wrapper);

        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        long total = teacherPage.getTotal();// 总记录数
        List<EduTeacher> records = teacherPage.getRecords();// 数据list集合

        return ResponseResult.ok().data("total", total).data("records", records);
    }

}

