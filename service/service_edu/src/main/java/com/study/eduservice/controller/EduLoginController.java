package com.study.eduservice.controller;

import com.study.commonutils.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:EduLoginController
 * @Auther: yao
 * @Description: 登录接口
 * @Date: 2022/8/20 15:50
 * @Version: v1.0
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @PostMapping("/login")
    public ResponseResult login() {
        return ResponseResult.ok();
    }

    @GetMapping("info")
    public ResponseResult info() {
        return ResponseResult.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "");
    }
}
