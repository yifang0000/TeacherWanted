package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.CourseCommentVo;

import java.util.List;

public interface CourseCommentDao {
    List<CourseCommentVo> findAll();
    List<CourseCommentVo> getCommentsByCourseId(Integer courseId);

    CourseCommentVo getCommentsById(Integer id);
    void createComment(CourseCommentVo courseComment);
    void editComment(CourseCommentVo courseComment);
    void updateComment(CourseCommentVo courseComment);

    void deleteComment(Integer id);

}
