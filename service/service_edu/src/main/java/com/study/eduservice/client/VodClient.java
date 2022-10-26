package com.study.eduservice.client;

import com.study.commonutils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    // 定义调用的方法路径
    // 根据视频id删除阿里云中的视频
    // todo 注意，@PathVariable注解一定要指定参数名称，否则会出错
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public ResponseResult removeAlyVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public ResponseResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
