package com.study.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.commonutils.ResponseResult;
import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName:TeacherFrontController
 * @Auther: yao
 * @Description: 讲师前台部分
 * @Date: 2024/3/17 22:54
 * @Version: v1.0
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
@EnableDiscoveryClient//nacos注册
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    // 分页查询讲师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public ResponseResult getTeacherFrontList(@PathVariable long page, @PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String,Object> map=teacherService.getTeacherFrontList(pageTeacher);
        // 返回分页所有数据
        return ResponseResult.ok().data(map);
    }

}
