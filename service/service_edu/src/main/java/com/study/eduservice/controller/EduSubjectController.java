package com.study.eduservice.controller;


import com.study.commonutils.ResponseResult;
import com.study.eduservice.service.EduSubjectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    @PostMapping("addSubject")
    public ResponseResult addSubject(MultipartFile file) {
        // 上传过来excel文件

        eduSubjectService.saveSubject(file, eduSubjectService);
        return ResponseResult.ok();
    }

}

