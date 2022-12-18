package com.study.educenter.controller;


import com.study.commonutils.JwtUtils;
import com.study.commonutils.ResponseResult;
import com.study.educenter.entity.UcenterMember;
import com.study.educenter.entity.vo.RegisterVo;
import com.study.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author yao
 * @since 2022-12-18
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    // 登录
    @PostMapping("login")
    public ResponseResult loginUser(@RequestBody UcenterMember member) {
        // 调用service方法实现登录
        // 返回token值，使用jwt生成
        String token = memberService.login(member);
        return ResponseResult.ok().data("token", token);
    }

    // 注册
    @PostMapping("register")
    public ResponseResult registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return ResponseResult.ok();
    }

    @GetMapping("getMemberInfo")
    public ResponseResult getMemberInfo(HttpServletRequest request) {
        // 调用jwt工具类的方法，根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库，根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return ResponseResult.ok().data("userInfo", member);
    }

}

