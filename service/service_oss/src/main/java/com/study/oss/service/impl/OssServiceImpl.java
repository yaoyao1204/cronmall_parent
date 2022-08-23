package com.study.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.study.oss.service.OssService;
import com.study.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName:OssServicempl
 * @Auther: yao
 * @Description:
 * @Date: 2022/8/22 22:11
 * @Version: v1.0
 */

@Service
public class OssServiceImpl implements OssService {

    // 上传头像到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();
            // 1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;
            // 2 把文件按照日期进行分类 例如：2019/11/12/01.jpg
            // 获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            // 拼接
            fileName = datePath + "/" + fileName;
            // 调用oss方法实现上传
            // 第二个参数，上传到oss文件路径和文件名称
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭ossClient
            ossClient.shutdown();
            // 把上传之后文件路径返回
            // 需要把上传到阿里云oss路径手动拼接出来
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
