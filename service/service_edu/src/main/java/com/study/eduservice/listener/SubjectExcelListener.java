package com.study.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.eduservice.entity.EduSubject;
import com.study.eduservice.entity.excel.SubjectData;
import com.study.eduservice.service.EduSubjectService;
import com.study.servicebase.exceptionhandler.CromMallException;

/**
 * @ClassName:SubjectExcelListener
 * @Auther: yao
 * @Description: easyexcel导入监听器
 * @Date: 2022/8/24 23:03
 * @Version: v1.0
 */

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    // 因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    // 不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }

    // 读取excel内容，一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) throw new CromMallException(20001, "文件数据为空");
        // 一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        // 判断一级分类是否重复
        EduSubject eduSubject = this.existOneSubject(this.subjectService, subjectData.getOneSubjectName());
        if (eduSubject == null) {// 没有相同一级分类，添加
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(eduSubject);
        }
        // 获取一级分类的id值
        String pid = eduSubject.getId();
        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }

    }

    // 判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name).eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    // 判断二级分类不能重读添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name).eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
