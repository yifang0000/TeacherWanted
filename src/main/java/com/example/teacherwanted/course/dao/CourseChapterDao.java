package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.CourseChapterVo;

import java.util.List;

public interface CourseChapterDao {
    List<CourseChapterVo> findByCourseId(Integer courseId);

    CourseChapterVo findById(Integer id);

    Integer createChapters(CourseChapterVo chapterVo);
    CourseChapterVo createChapter(CourseChapterVo updatedChapter);

    Integer updateChapters(CourseChapterVo chapterVo);
    void updateChapter(Integer courseId, CourseChapterVo chapterVo);

    Integer deleteChaptersById(Integer courseId);


    void deleteChapterByOrderId(CourseChapterVo chapterVo);
}
