package com.study.eduservice.controller;


import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public List<EduTeacher> findAllTeacher() {
        // 调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    // 2.讲师逻辑删除功能  1)添加逻辑删除的配置 2）删除的实体类字段加注解@TableLogic 3）写controller的方法
    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除讲师")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return b;
    }


}

