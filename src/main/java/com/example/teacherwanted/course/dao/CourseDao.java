package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.CourseVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseDao {
//    Integer countCourse(CourseQueryParams courseQueryParams);
    List<CourseVo> getCourses(int page, int pageSize, Integer courseCategoryId, String keyword);
    CourseVo getCourseById(Integer courseId);

    List<CourseVo> getCoursesByKeyword(String keyword);
    Map<String, Object> getAllCourses(int page, int pageSize, Integer courseCategoryId, String keyword);

    Map<String, Object> getCoursesByTeacher(Integer teaId, int page, int pageSize, Integer courseCategoryId, String keyword);

    Integer createCourse(CourseVo courseVo) throws IOException;

    void updateCourseStatus(Integer courseId, CourseVo courseRequest);

    void updateCourse(Integer courseId, CourseVo courseRequest);

    void deleteCourseById(Integer courseId);

}
