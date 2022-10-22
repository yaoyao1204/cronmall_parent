package com.study.vod.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName:VodService
 * @Auther: yao
 * @Description: 上传视频的实现类
 * @Date: 2022/10/17 22:51
 * @Version: v1.0
 */

public interface VodService {
    String uploadVideo(MultipartFile file);
}
