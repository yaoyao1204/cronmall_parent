package com.study.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.mapper.EduTeacherMapper;
import com.study.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yao
 * @since 2022-08-10
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        // 把分页数据封装到pageTeacher对象
        baseMapper.selectPage(pageTeacher, wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long current = pageTeacher.getCurrent();
        // 当前是否有下一页
        boolean hasNext = pageTeacher.hasNext();
        // 当前是否有上一页
        boolean hasPrevious = pageTeacher.hasPrevious();
        // 获取分页数据，放入map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasNext", hasNext);
        return map;
    }

}
