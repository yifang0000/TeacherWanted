package com.example.teacherwanted.course.service;

import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CourseOrderService {
    List<CourseOrderVo> findAll();
    CourseOrderVo getCourseOrderById(Integer id);
    List<CourseOrderVo> getCourseOrdesByMemId(Integer memId);
    void createCourseOrder(CourseOrderVo courseOrder);
    void updateCourseOrder(CourseOrderVo courseOrder);
    void deleteCourseOrder(Integer id);
    List<CourseOrderDetailVo>getFeedbackByCourseId(Integer courseId);
    CourseOrderDetailVo getOrderDetailById(Integer id);
    void createFeedback(CourseOrderDetailVo courseOrderDetail);
    void updateFeedback(CourseOrderDetailVo courseOrderDetail);
    void deleteFeedback(Integer id);

}
