package com.study.eduservice.service;

import com.study.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.eduservice.entity.chapter.ChapterVo;
import com.study.eduservice.entity.chapter.VideoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideo(String courseId);

    Boolean deleteChapter(String chapterId);

    void removeChapterService(String courseId);
}
