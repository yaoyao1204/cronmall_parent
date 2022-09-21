package com.study.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:OneSubject
 * @Auther: yao
 * @Description: 一级分类实体类
 * @Date: 2022/8/29 22:08
 * @Version: v1.0
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    // 一个一级分类有多个二级分类
    private List<TwoSubject> children=new ArrayList<>();


}
