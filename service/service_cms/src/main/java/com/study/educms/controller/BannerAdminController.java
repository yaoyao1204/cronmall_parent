package com.study.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.commonutils.ResponseResult;
import com.study.educms.entity.CrmBanner;
import com.study.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 后台banner管理接口
 * </p>
 *
 * @author yao
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    // 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public ResponseResult pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> bannerPage = new Page<>(page, limit);
        crmBannerService.page(bannerPage, null);
        return ResponseResult.ok().data("items", bannerPage.getRecords()).data("total", bannerPage.getTotal());
    }

    // 添加banner
    @PostMapping("addBanner")
    public ResponseResult addBanner(@RequestBody CrmBanner banner) {
        crmBannerService.save(banner);
        return ResponseResult.ok();
    }

    // 获取banner
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return ResponseResult.ok().data("banner", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResponseResult updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return ResponseResult.ok();
    }

}

