package com.study.educms.controller;


import com.study.commonutils.ResponseResult;
import com.study.educms.entity.CrmBanner;
import com.study.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前台控制接口
 * </p>
 *
 * @author yao
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    // 查询所有banner
    @Cacheable(value="banner",key="'selectIndexList'")
    @GetMapping("getAllBanner")
    public ResponseResult getAllBanner() {
        List<CrmBanner> banners = bannerService.selectAllBanner();
        return ResponseResult.ok().data("list", banners);
    }

}

