package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.dao.CourseOrderDao;
import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;
import com.example.teacherwanted.course.service.CourseOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class CourseOrderServiceImpl implements CourseOrderService {
    @Autowired
    private CourseOrderDao courseOrderDao;

    @Override
    public List<CourseOrderVo> findAll() {
        return courseOrderDao.findAll();
    }

    @Override
    public CourseOrderVo getCourseOrderById(Integer id) {
        return courseOrderDao.getCourseOrderById(id);
    }

    @Override
    public List<CourseOrderVo> getCourseOrdersByMemId(Integer memId) {
        return courseOrderDao.getCourseOrdersByMemId(memId);
    }

    @Override
    public Integer createCourseOrder(CourseOrderVo courseOrder) {
        return courseOrderDao.createCourseOrder(courseOrder);
    }

    @Override
    public void createOrderDetail(CourseOrderDetailVo courseOrderDetail) {
        courseOrderDao.createOrderDetail(courseOrderDetail);
    }

    @Override
    public void updateCourseOrder(CourseOrderVo courseOrder) {
        courseOrderDao.updateCourseOrder(courseOrder);
    }

    @Override
    public void deleteCourseOrder(Integer id) {
        courseOrderDao.deleteCourseOrder(id);
    }

    @Override
    public List<CourseOrderDetailVo> getOrderDetailsByMemId(Integer memId) {
        return courseOrderDao.getOrderDetailsByMemId(memId);
    }

    @Override
    public List<CourseOrderDetailVo> getFeedbackByCourseId(Integer courseId) {
        return courseOrderDao.getFeedbackByCourseId(courseId);
    }

    @Override
    public CourseOrderDetailVo getOrderDetailById(Integer id) {
        return courseOrderDao.getOrderDetailById(id);
    }

    @Override
    public void createFeedback(CourseOrderDetailVo courseOrderDetail) {
        courseOrderDao.createFeedback(courseOrderDetail);
    }

    @Override
    public void newFeedback(CourseOrderDetailVo detail) {
        courseOrderDao.newFeedback(detail);
    }

    @Override
    public void updateFeedback(CourseOrderDetailVo courseOrderDetail) {
        courseOrderDao.updateFeedback(courseOrderDetail);
    }

    @Override
    public void deleteFeedback(CourseOrderDetailVo courseOrderDetail) {
        courseOrderDao.deleteFeedback(courseOrderDetail);
    }

    @Override
    public void deleteOrderDetail(Integer id) {
        courseOrderDao.deleteOrderDetail(id);
    }
}
