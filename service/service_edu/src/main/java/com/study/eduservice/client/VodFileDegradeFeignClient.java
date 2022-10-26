package com.study.eduservice.client;

import com.study.commonutils.ResponseResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName:VodFileDegradeFeignClient
 * @Auther: yao
 * @Description: 调用接口容错的方法，调用接口出错才会执行的代码
 * @Date: 2022/10/25 23:56
 * @Version: v1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public ResponseResult removeAlyVideo(String id) {
        return ResponseResult.error().message("删除视频出错了");
    }

    @Override
    public ResponseResult deleteBatch(List<String> videoIdList) {
        return ResponseResult.error().message("删除多个视频出错了");
    }
}
