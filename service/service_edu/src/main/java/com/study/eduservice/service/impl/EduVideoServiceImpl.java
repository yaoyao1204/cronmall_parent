package com.study.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.study.eduservice.client.VodClient;
import com.study.eduservice.entity.EduVideo;
import com.study.eduservice.mapper.EduVideoMapper;
import com.study.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    // todo 删除小节的时候，删除对应视频文件
    @Override
    public void removeVideoByCourseId(String courseId) {
        // 根据课程id查询所有的视频id
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> list = baseMapper.selectList(queryWrapper);
        List<String> idList = new ArrayList<>();
        for (EduVideo video : list) {
            if (!StringUtils.isNullOrEmpty(video.getVideoSourceId())){
                idList.add(video.getVideoSourceId());
            }
        }
        if (!CollectionUtils.isEmpty(idList)){
            try {
                vodClient.deleteBatch(idList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getClass());
            }
        }
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
