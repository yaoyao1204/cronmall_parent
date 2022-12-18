package com.study.educenter.service;

import com.study.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author yao
 * @since 2022-12-18
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);
}
