package com.study.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.commonutils.JwtUtils;
import com.study.commonutils.MD5;
import com.study.educenter.entity.UcenterMember;
import com.study.educenter.entity.vo.RegisterVo;
import com.study.educenter.mapper.UcenterMemberMapper;
import com.study.educenter.service.UcenterMemberService;
import com.study.servicebase.exceptionhandler.CromMallException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author yao
 * @since 2022-12-18
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        // 获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        // 手机号和密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new CromMallException(20001, "登录失败");
        }
        // 判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null) {
            throw new CromMallException(20001, "登录失败");
        }
        // 判断密码
        // 存储到数据库中的密码做了加密，需要把输入的密码进行加密再比较
        // 加密方式：MD5
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())) {
            throw new CromMallException(20001, "登录失败");
        }
        if (ucenterMember.getIsDisabled()) {
            throw new CromMallException(20001, "登录失败");
        }
        // 登录成功
        // 生成token字符串,使用jwt工具类
        return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
    }

    // 注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        // 获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new CromMallException(20001, "注册失败");
        }
        // 获取redis中的密码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new CromMallException(20001, "注册失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new CromMallException(20001, "注册失败");
        }
        // 添加
        UcenterMember member = new UcenterMember();
        member.setPassword(MD5.encrypt(password));
        member.setIsDeleted(false);
        member.setIsDisabled(false);
        member.setNickname(nickname);
        member.setMobile(mobile);
        baseMapper.insert(member);
    }
}
