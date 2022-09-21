package com.study.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.commonutils.ResponseResult;
import com.study.eduservice.entity.EduVideo;
import com.study.eduservice.service.EduVideoService;
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

    // 添加小节
    @PostMapping("addVideo")
    public ResponseResult addVideo(@RequestBody EduVideo video) {
        videoService.save(video);
        return ResponseResult.ok();
    }

    // 删除小节
    @DeleteMapping("deleteVideo/{id}")
    public ResponseResult deleteVideo(@PathVariable String id) {
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
    public ResponseResult getVideo(@PathVariable String videoId){

        EduVideo video = videoService.getById(videoId);

        return ResponseResult.ok().data("video",video);
    }


}

