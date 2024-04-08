package com.study.eduservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.study.eduservice.entity.EduSubject;
import com.study.eduservice.entity.subject.OneSubject;
import com.study.eduservice.entity.subject.TwoSubject;
import com.study.eduservice.entity.excel.SubjectData;
import com.study.eduservice.entity.subject.TwoSubjectSaveDto;
import com.study.eduservice.listener.SubjectExcelListener;
import com.study.eduservice.mapper.EduSubjectMapper;
import com.study.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yao
 * @since 2022-08-24
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {




    // 添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // 课程分类列表（树形）
    @Override
    public List<OneSubject> getAllSubject() {
        // 查询出所有一级分类 parent_id=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        // 查询出所有二级分类 parent_id!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        // 创建list集合，用于存储最终封装数据
        ArrayList<OneSubject> finalSubjectList = new ArrayList<>();
        // 封装一级分类
        for (EduSubject eduSubject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject, oneSubject);
            finalSubjectList.add(oneSubject);
            // 在一级分类的循环里面遍历查询所有的二级分类
            ArrayList<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int i = 0; i < twoSubjectList.size(); i++) {
                // 获取每个二级分类
                EduSubject subject = twoSubjectList.get(i);
                // 判断二级分类的parentId和一级分类的id是否一致
                if(subject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);

        }

        return finalSubjectList;
    }

    @Override
    public Boolean saveOneSubject(OneSubject bo) {
        //连续点点击 redis 控制
        //  redis.get(id)
        //重复炒作
        String id = bo.getId();
        // redis.set()


        //mysql 控制唯一数据查询表
        //select * id
        if (CollUtil.isNotEmpty(bo.getChildren())) {
            return false;
        }



        List<TwoSubjectSaveDto> list = bo.getChildren().stream()
                .filter(data -> data.getId() != null)
                .map(data -> {
                    TwoSubjectSaveDto dto = new TwoSubjectSaveDto();
                    BeanUtil.copyProperties(data, dto);
                    dto.setTitle2(bo.getId());
                    dto.setTitle3(bo.getTitle());
                    return dto;
                }).collect(Collectors.toList());

        Lists.partition(list,1000);

        return null;
    }



}
