package com.study.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:Chapter
 * @Auther: yao
 * @Description: 章节
 * @Date: 2022/9/3 22:46
 * @Version: v1.0
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    // 表示小节
    private List<VideoVo> children = new ArrayList<>();
}
