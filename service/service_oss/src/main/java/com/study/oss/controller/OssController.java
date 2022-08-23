package com.study.oss.controller;

import com.study.commonutils.ResponseResult;
import com.study.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName:OssController
 * @Auther: yao
 * @Description:
 * @Date: 2022/8/22 22:11
 * @Version: v1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Resource
    private OssService ossService;

    // 上传头像的方法
    @PostMapping
    public ResponseResult uploadOssFile(MultipartFile file) {
        // 获取上传文件 MultipartFile
        // 返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return ResponseResult.ok().data("url", url);
    }
}
