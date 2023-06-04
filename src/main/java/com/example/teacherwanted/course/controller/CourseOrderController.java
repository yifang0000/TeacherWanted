package com.example.teacherwanted.course.controller;


import com.example.teacherwanted.course.model.vo.CourseCommentVo;
import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;
import com.example.teacherwanted.course.service.CourseOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseOrderController {
    @Autowired
    private CourseOrderService courseOrderService;

    @GetMapping("/course_order/{id}")
    public ResponseEntity<CourseOrderVo> getCourseOrderById(@PathVariable Integer id) {
        CourseOrderVo courseOrder = courseOrderService.getCourseOrderById(id);
        if (courseOrder != null) {
            return ResponseEntity.ok(courseOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/course_order")
    public ResponseEntity<Void> createCourseOrder(@RequestBody @Valid CourseOrderVo courseOrder) {
        courseOrderService.createCourseOrder(courseOrder);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/course_order/{id}")
    public ResponseEntity<Void> updateCourseOrder(@PathVariable Integer id, @RequestBody @Valid CourseOrderVo courseOrder) {
        courseOrder.setOrderId(id);
        courseOrderService.updateCourseOrder(courseOrder);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/course_order/{id}")
    public ResponseEntity<Void> deleteCourseOrder(@PathVariable Integer id) {
        courseOrderService.deleteCourseOrder(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order_detail/{courseId}")
    public ResponseEntity<List<CourseOrderDetailVo>> getFeedbackByCourseId(@PathVariable Integer courseId) {
        List<CourseOrderDetailVo> courseOrderDetails = courseOrderService.getFeedbackByCourseId(courseId);
        return ResponseEntity.ok(courseOrderDetails);

    }
    @PostMapping("/order_detail")
    public ResponseEntity<Void> createFeedback(@RequestBody @Valid CourseOrderDetailVo courseOrderDetail) {
        courseOrderService.createFeedback(courseOrderDetail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/order_detail/{id}")
    public ResponseEntity<Void> updateFeedback(@PathVariable Integer id, @RequestBody @Valid CourseOrderDetailVo courseOrderDetail) {
        courseOrderDetail.setOrderId(id);
        courseOrderService.updateFeedback(courseOrderDetail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/order_detail/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        courseOrderService.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }
}
