package com.study.eduservice.controller;


import com.study.commonutils.ResponseResult;
import com.study.eduservice.entity.subject.OneSubject;
import com.study.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yao
 * @since 2022-08-24
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    // 添加课程分类
    // 获取到上传过来的文件，把文件内容读取出来
    @ApiOperation("添加课程分类")
    @PostMapping("addSubject")
    public ResponseResult addSubject(MultipartFile file) {
        // 上传过来excel文件

        eduSubjectService.saveSubject(file, eduSubjectService);
        return ResponseResult.ok();
    }

    // 课程分类列表
    @ApiOperation("获取课程分类列表")
    @GetMapping("getSubject")
    public ResponseResult getSubject() {
        List<OneSubject> list = eduSubjectService.getAllSubject();
        return ResponseResult.ok().data("list", list);
    }


}

