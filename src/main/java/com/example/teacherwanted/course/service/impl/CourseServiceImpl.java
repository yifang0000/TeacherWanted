package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.CourseDao;
import com.example.teacherwanted.course.model.vo.CourseVo;
import com.example.teacherwanted.course.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

//    @Override
//    public Integer countCourse(CourseQueryParams courseQueryParams) {
//        return courseDao.countCourse(courseQueryParams);
//    }
    @Override
    public List<CourseVo> getCourses(int page, int pageSize, Integer courseCategoryId, String keyword) {
        return courseDao.getCourses(page, pageSize, courseCategoryId, keyword);
    }

    @Override
    public CourseVo getCourseById(Integer courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public List<CourseVo> getCoursesByKeyword(String keyword) {
        return courseDao.getCoursesByKeyword(keyword);
    }

    @Override
    public Map<String, Object> getAllCourses(int page, int pageSize, Integer courseCategoryId, String keyword) {
        return courseDao.getAllCourses( page, pageSize, courseCategoryId, keyword);
    }

    @Override
    public Map<String, Object> getCoursesByTeacher(Integer teaId, int page, int pageSize, Integer courseCategoryId, String keyword) {
        return courseDao.getCoursesByTeacher(teaId, page, pageSize, courseCategoryId, keyword);
    }

    @Override
    public Integer createCourse(CourseVo courseVo) throws IOException {
        return courseDao.createCourse(courseVo);
    }

    @Override
    public void updateCourseStatus(Integer courseId, CourseVo courseRequest) {
        courseDao.updateCourseStatus(courseId, courseRequest);
    }

    @Override
    public void updateCourse(Integer courseId, CourseVo courseRequest) {
        courseDao.updateCourse(courseId, courseRequest);
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseDao.deleteCourseById(courseId);
    }




}
