package com.study.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.study.vod.service.VodService;
import com.study.vod.utils.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName:VodServiceImpl
 * @Auther: yao
 * @Description: 上传视频的实现类
 * @Date: 2022/10/17 22:52
 * @Version: v1.0
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        String videoId = null;
        try {
            // title 上传之后显示的文件名
            // fileName 上传的文件名
            //inputStream 上传文件的输入流
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String title = originalFilename.substring(0,originalFilename.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.println("requestId=" + response.getRequestId());
            videoId = "";
            if (response.isSuccess()) {
                videoId = response.getVideoId();
                System.out.println("VideoId=" + response.getVideoId());
            } else {
                videoId = response.getVideoId();
                System.out.println("VideoId=" + response.getVideoId());
                System.out.println("ErrorCode=" + response.getCode());
                System.out.println("ErrorMessage=" + response.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoId;
    }
}
