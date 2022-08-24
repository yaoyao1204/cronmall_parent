package com.study.codegen.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName:ExcelListener
 * @Auther: yao
 * @Description: excel监听
 * @Date: 2022/8/24 22:40
 * @Version: v1.0
 */

public class ExcelListener extends AnalysisEventListener<DemoData> {
    // 一行一行读取excel内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("***" + demoData);
    }

    // 读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
