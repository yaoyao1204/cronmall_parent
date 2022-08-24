package com.study.codegen.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName:DemoData
 * @Auther: yao
 * @Description: 测试数据实体类
 * @Date: 2022/8/24 22:21
 * @Version: v1.0
 */

@Data
public class DemoData {
    // 设置excel表头名称
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String sname;
}
