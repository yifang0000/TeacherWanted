package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.CourseOrderDao;
import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CourseOrderDaoImpl implements CourseOrderDao {
    @Override
    public CourseOrderVo getCourseOrderById(Integer id) {
        return null;
    }

    @Override
    public void createCourseOrder(CourseOrderVo courseOrder) {

    }

    @Override
    public void updateCourseOrder(CourseOrderVo courseOrder) {

    }

    @Override
    public void deleteCourseOrder(Integer id) {

    }

    @Override
    public List<CourseOrderDetailVo> getFeedbackByCourseId(Integer courseId) {
        return null;
    }

    @Override
    public void createFeedback(CourseOrderDetailVo courseOrderDetail) {

    }

    @Override
    public void updateFeedback(CourseOrderDetailVo courseOrderDetail) {

    }

    @Override
    public void deleteFeedback(Integer id) {

    }
}
