package com.study.codegen.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:TestEasyExcel
 * @Auther: yao
 * @Description: 导入测试类
 * @Date: 2022/8/24 22:24
 * @Version: v1.0
 */

public class TestEasyExcel {
    public static void main(String[] args) {
        // 实现excel写的操作
        // 1 设置写入文件夹地址和excel文件名称
//        String filename = "F:\\write.xlsx";
        // 2 调用easyexcel里面的方法实现写操作
        // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
//        EasyExcel.write(filename, DemoData.class)
//                .sheet("学生列表").doWrite(getData());

        // 实现excel读操作
        String filename = "F:\\write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();

    }

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
