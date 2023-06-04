package com.example.teacherwanted.course.service;

import com.example.teacherwanted.course.model.vo.CourseCommentVo;

import java.util.List;

public interface CourseCommentService {
    List<CourseCommentVo> findAll();
    List<CourseCommentVo> getCommentsByCourseId(Integer courseId);

    CourseCommentVo getCommentsById(Integer id);
    void createComment(CourseCommentVo courseComment);
    void editComment(CourseCommentVo courseComment);
    void updateComment(CourseCommentVo courseComment);

    void deleteComment(Integer id);

}
