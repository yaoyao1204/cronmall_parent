package com.study.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName:OssService
 * @Auther: yao
 * @Description:
 * @Date: 2022/8/22 22:11
 * @Version: v1.0
 */

public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
