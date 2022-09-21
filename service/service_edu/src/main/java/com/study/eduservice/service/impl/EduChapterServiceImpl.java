package com.study.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.eduservice.entity.EduChapter;
import com.study.eduservice.entity.EduVideo;
import com.study.eduservice.entity.chapter.ChapterVo;
import com.study.eduservice.entity.chapter.VideoVo;
import com.study.eduservice.mapper.EduChapterMapper;
import com.study.eduservice.service.EduChapterService;
import com.study.eduservice.service.EduVideoService;
import com.study.servicebase.exceptionhandler.CromMallException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        // 1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);
        // 2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<EduVideo> videoList = videoService.list(queryWrapper);
        // 3 遍历查询章节list集合进行封装
        List<ChapterVo> list = new ArrayList<>();

        for (int i = 0; i < chapterList.size(); i++) {
            // 章节
            EduChapter chapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            // 把chapterVo放到最终list集合
            list.add(chapterVo);
            // 4 遍历查询小节list集合，进行封装
            // 创建一个集合
            ArrayList<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                // 得到每个小节
                EduVideo video = videoList.get(j);
                // 判断：小节里面的chapterId和章节里的id是否一样
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return list;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        // 如果章节下有小节，不让删除，如果没有小节，则删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) {
            throw new CromMallException(20001, "章节下有小节，无法删除！");
        } else {
            int i = baseMapper.deleteById(chapterId);
            return i > 0;
        }
    }


}
