package com.study.eduservice.controller;


import com.study.commonutils.ResponseResult;
import com.study.eduservice.client.VodClient;
import com.study.eduservice.entity.EduVideo;
import com.study.eduservice.service.EduVideoService;
import com.study.servicebase.exceptionhandler.CromMallException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    // 注入VodClient
    @Autowired
    private VodClient vodClient;

    // 添加小节
    @PostMapping("addVideo")
    public ResponseResult addVideo(@RequestBody EduVideo video) {
        videoService.save(video);
        return ResponseResult.ok();
    }

    // 删除小节
    // 删除小节的时候，调用微服务，把阿里云里面的视频删除
    @DeleteMapping("deleteVideo/{id}")
    public ResponseResult deleteVideo(@PathVariable String id) {
        // 根据小节id获取视频id，调用方法实现视频删除
        EduVideo video = videoService.getById(id);
        if (!StringUtils.isEmpty(video.getVideoSourceId())) {
            ResponseResult result = vodClient.removeAlyVideo(video.getVideoSourceId());
            if (result.getCode() == 20001) {
                throw new CromMallException(20001, "删除视频失败，熔断器。。");
            }
        }
        videoService.removeById(id);
        return ResponseResult.ok();
    }

    // 修改小节
    @PostMapping("updateVideo")
    public ResponseResult updateVideo(@RequestBody EduVideo video) {
        videoService.updateById(video);
        return ResponseResult.ok();
    }

    // 查看小节
    @GetMapping("getVideo/{videoId}")
    public ResponseResult getVideo(@PathVariable String videoId) {

        EduVideo video = videoService.getById(videoId);

        return ResponseResult.ok().data("video", video);
    }


}

