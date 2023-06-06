package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;

import java.util.List;

public interface CourseOrderDao {
    CourseOrderVo getCourseOrderById(Integer id);

    void createCourseOrder(CourseOrderVo courseOrder);

    void updateCourseOrder(CourseOrderVo courseOrder);

    void deleteCourseOrder(Integer id);

    List<CourseOrderDetailVo> getFeedbackByCourseId(Integer courseId);
    CourseOrderDetailVo getOrderDetailById(Integer id);
    void createFeedback(CourseOrderDetailVo courseOrderDetail);

    void updateFeedback(CourseOrderDetailVo courseOrderDetail);

    void deleteFeedback(Integer id);

}
