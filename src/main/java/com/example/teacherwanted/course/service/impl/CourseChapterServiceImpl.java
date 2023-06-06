package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.CourseChapterDao;
import com.example.teacherwanted.course.model.vo.CourseChapterVo;
import com.example.teacherwanted.course.service.CourseChapterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseChapterServiceImpl implements CourseChapterService {
    @Autowired
    private CourseChapterDao courseChapterDao;

    @Override
    public List<CourseChapterVo> findByCourseId(Integer courseId) {
        return courseChapterDao.findByCourseId(courseId);
    }
    @Override
    public CourseChapterVo findById(Integer id) {
        return courseChapterDao.findById(id);
    }

    @Override
    public Integer createChapters(CourseChapterVo chapterVo) {
        return courseChapterDao.createChapters(chapterVo);
    }

    @Override
    public CourseChapterVo createChapter(CourseChapterVo updatedChapter) {
        return courseChapterDao.createChapter(updatedChapter);
    }

    @Override
    public Integer updateChapters(CourseChapterVo chapterVo) {
        return courseChapterDao.updateChapters(chapterVo);
    }

    @Override
    public void updateChapter(Integer courseId, CourseChapterVo chapterVo) {
        courseChapterDao.updateChapter(courseId, chapterVo);
    }

    @Override
    public Integer deleteChaptersById(Integer courseId) {
        return courseChapterDao.deleteChaptersById(courseId);
    }

    @Override
    public void deleteChapterByOrderId(CourseChapterVo chapterVo) {
        courseChapterDao.deleteChapterByOrderId(chapterVo);
    }
}
