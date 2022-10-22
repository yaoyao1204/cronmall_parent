package com.study.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.study.commonutils.ResponseResult;
import com.study.servicebase.exceptionhandler.CromMallException;
import com.study.vod.service.VodService;
import com.study.vod.utils.ConstantVodUtils;
import com.study.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName:VodController
 * @Auther: yao
 * @Description: 上传视频
 * @Date: 2022/10/17 22:49
 * @Version: v1.0
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
@Api(description = "上传视频")
public class VodController {
    @Resource
    private VodService vodService;

    // 上传视频到阿里云
    @PostMapping("uploadVideo")
    public ResponseResult uploadVideo(MultipartFile file) {
        String id = vodService.uploadVideo(file);
        return ResponseResult.ok().data("videoId", id);
    }

    // 根据视频id删除阿里云中的视频
    @DeleteMapping("removeAlyVideo/{id}")
    public ResponseResult removeAlyVideo(@PathVariable String id) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 向request设置视频id
            request.setVideoIds(id);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return ResponseResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CromMallException(20001, "删除视频失败");
        }
    }


}
