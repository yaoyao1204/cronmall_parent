package com.study.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName:SubjectData
 * @Auther: yao
 * @Description: 创建跟excel对应的实体类
 * @Date: 2022/8/24 22:57
 * @Version: v1.0
 */

@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

}
