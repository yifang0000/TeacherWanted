package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.CourseCommentDao;
import com.example.teacherwanted.course.model.vo.CourseCommentVo;
import com.example.teacherwanted.course.service.CourseCommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseCommentServiceImpl implements CourseCommentService {
    @Autowired
    private CourseCommentDao courseCommentDao;
    @Override
    public List<CourseCommentVo> findAll() {
        return courseCommentDao.findAll();
    }

    @Override
    public List<CourseCommentVo> getCommentsByCourseId(Integer courseId) {
        return courseCommentDao.getCommentsByCourseId(courseId);
    }

    @Override
    public CourseCommentVo getCommentsById(Integer id) {
        return courseCommentDao.getCommentsById(id);
    }

    @Override
    public void createComment(CourseCommentVo courseComment) {
        courseCommentDao.createComment(courseComment);
    }

    @Override
    public void editComment(CourseCommentVo courseComment) {
        courseCommentDao.editComment(courseComment);
    }

    @Override
    public void updateComment(CourseCommentVo courseComment) {
        courseCommentDao.updateComment(courseComment);
    }

    @Override
    public void deleteComment(Integer id) {
        courseCommentDao.deleteComment(id);
    }
}
