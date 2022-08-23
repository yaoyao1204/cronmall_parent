package com.study.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName:ConstantPropertiesUtils
 * @Auther: yao
 * @Description: 读取配置文件的工具类
 * @Date: 2022/8/22 21:57
 * @Version: v1.0
 */
// 当项目已启动，spring接口，spring加载之后，执行接口中的一个方法
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    // 读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    // 定义公开静态常量
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
