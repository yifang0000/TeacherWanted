package com.example.teacherwanted.course.dao;

import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;

import java.util.List;

public interface CourseOrderDao {
    List<CourseOrderVo> findAll();
    CourseOrderVo getCourseOrderById(Integer id);
    List<CourseOrderVo> getCourseOrdersByMemId(Integer memId);

    Integer createCourseOrder(CourseOrderVo courseOrder);
    void createOrderDetail(CourseOrderDetailVo courseOrderDetail);

    void updateCourseOrder(CourseOrderVo courseOrder);

    void deleteCourseOrder(Integer id);

    List<CourseOrderDetailVo> getOrderDetailsByMemId(Integer memId);
    List<CourseOrderDetailVo> getFeedbackByCourseId(Integer courseId);
    CourseOrderDetailVo getOrderDetailById(Integer id);
    void createFeedback(CourseOrderDetailVo courseOrderDetail);
    void newFeedback(CourseOrderDetailVo detail);

    void updateFeedback(CourseOrderDetailVo courseOrderDetail);

    void deleteFeedback(CourseOrderDetailVo courseOrderDetail);

}
