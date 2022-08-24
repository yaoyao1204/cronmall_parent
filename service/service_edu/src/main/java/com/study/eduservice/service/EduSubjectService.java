package com.study.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.eduservice.entity.EduSubject;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yao
 * @since 2022-08-24
 */
public interface EduSubjectService extends IService<EduSubject> {

    // 添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService);
}
