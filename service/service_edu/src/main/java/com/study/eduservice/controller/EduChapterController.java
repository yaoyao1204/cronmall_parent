package com.study.eduservice.controller;


import com.study.commonutils.ResponseResult;
import com.study.eduservice.entity.EduChapter;
import com.study.eduservice.entity.chapter.ChapterVo;
import com.study.eduservice.service.EduChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yao
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Resource
    private EduChapterService chapterService;

    // 课程大纲列表 根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public ResponseResult getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideo(courseId);
        return ResponseResult.ok().data("allChapterVideo", list);
    }
    // 添加章节
    @PostMapping("addChapter")
    public ResponseResult addChapter(@RequestBody EduChapter chapter){

        chapterService.save(chapter);
        return ResponseResult.ok();
    }

    // 根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public ResponseResult getChapterInfo(@PathVariable String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return ResponseResult.ok().data("chapter",chapter);
    }

    // 修改章节
    @PostMapping("updateChapter")
    public ResponseResult updateChapter(@RequestBody EduChapter chapter){
        chapterService.updateById(chapter);
        return ResponseResult.ok();
    }
    // 删除的方法
    @DeleteMapping("deleteChapter/{chapterId}")
    public ResponseResult deleteChapter(@PathVariable String chapterId){
        Boolean b = chapterService.deleteChapter(chapterId);
        return ResponseResult.ok();
    }

}

