package com.study.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.study.servicebase.exceptionhandler.CromMallException;
import com.study.vod.service.VodService;
import com.study.vod.utils.ConstantVodUtils;
import com.study.vod.utils.InitVodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
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

    @Override
    public void removeMoreAlyVideo(List videoIdList) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            String ids = StringUtils.join(videoIdList.toArray(), ",");
            // 向request设置视频id
            request.setVideoIds(ids);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CromMallException(20001, "删除视频失败");
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("34915c2dc9be486c99848d39b0886a9e");
        list.add("2b887dc9584d4dc68908780ec57cd3b9");
        String ids = StringUtils.join(list.toArray(), ",");
        System.out.println(ids);
    }
}
