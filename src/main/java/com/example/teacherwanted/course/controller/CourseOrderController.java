package com.example.teacherwanted.course.controller;


import com.example.teacherwanted.course.model.dto.CommentsResponse;
import com.example.teacherwanted.course.model.vo.*;
import com.example.teacherwanted.course.service.CourseOrderService;
import com.example.teacherwanted.course.service.CourseService;
import com.example.teacherwanted.course.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseOrderController {
    @Autowired
    private CourseOrderService courseOrderService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private MemberService memberService;
    @GetMapping("/course_orders")
    public ResponseEntity<List<CourseOrderVo>> findAll() {
        List<CourseOrderVo> courseOrderList = courseOrderService.findAll();
        return new ResponseEntity<>(courseOrderList, HttpStatus.OK);
    }
    @GetMapping("/course_order/{id}")
    public ResponseEntity<CourseOrderVo> getCourseOrderById(@PathVariable Integer id) {
        CourseOrderVo courseOrder = courseOrderService.getCourseOrderById(id);
        if (courseOrder != null) {
            return ResponseEntity.ok(courseOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/course_orders/{memId}")
    public ResponseEntity<List<CourseOrderVo>> getCourseOrdersByMemId(@PathVariable("memId") Integer memId) {
        List<CourseOrderVo> courseOrders = courseOrderService.getCourseOrdersByMemId(memId);
        return new ResponseEntity<>(courseOrders, HttpStatus.OK);
    }

    //建立訂單
    @PostMapping("/course_order")
    public ResponseEntity<CourseOrderVo> createCourseOrder(@RequestBody @Valid CourseOrderVo courseOrder) {
        Integer orderId = courseOrderService.createCourseOrder(courseOrder);
        CourseOrderVo createdOrder = courseOrderService.getCourseOrderById(orderId);
        return new ResponseEntity<>(createdOrder, HttpStatus.OK);
    }

    //建立訂單明細
    @PostMapping("/order_detail")
    public ResponseEntity<Void> createOrderDetail(@RequestBody @Valid CourseOrderDetailVo courseOrderDetail) {
        courseOrderService.createOrderDetail(courseOrderDetail);
        CourseVo courseVo = courseService.getCourseById(courseOrderDetail.getCourseId());
        courseVo.setBoughtCount(courseVo.getBoughtCount() + 1);
        courseService.updateBoughtCount(courseVo);
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

    @GetMapping("/orderdetails/{memId}")
    public ResponseEntity<List<CourseOrderDetailVo>> getOrderDetailsByMemId(@PathVariable("memId") Integer memId) {
        List<CourseOrderDetailVo> courseOrders = courseOrderService.getOrderDetailsByMemId(memId);
        return new ResponseEntity<>(courseOrders, HttpStatus.OK);
    }

    @GetMapping("/feedback/{courseId}")
    public ResponseEntity<CommentsResponse<CourseOrderDetailVo, MemberVo>> getFeedbackByCourseId(@PathVariable Integer courseId) {
        List<CourseOrderDetailVo> courseOrderDetails = courseOrderService.getFeedbackByCourseId(courseId);
        List<MemberVo> members = new ArrayList<>();
        for(CourseOrderDetailVo feedback : courseOrderDetails){
            MemberVo member = memberService.findById(feedback.getMemId());
            members.add(member);
        }
        CommentsResponse<CourseOrderDetailVo, MemberVo> response = new CommentsResponse<>(courseOrderDetails, members);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/feedback")
    public ResponseEntity<Void> createFeedback(@RequestBody @Valid CourseOrderDetailVo courseOrderDetail) {
        Integer courseId = courseOrderDetail.getCourseId();
        CourseVo courseVo = courseService.getCourseById(courseId);
        Integer rank = courseVo.getCourseTotalRank() + courseOrderDetail.getCourseRank();
        Integer totalEvaluate = courseVo.getCourseTotalEvaluate() + 1;
        courseVo.setCourseTotalRank(rank);
        courseVo.setCourseTotalEvaluate(totalEvaluate);
        courseService.updateCourse(courseId, courseVo);
        courseOrderService.createFeedback(courseOrderDetail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/feedback")
    public ResponseEntity<Void> newFeedback(@RequestBody @Valid CourseOrderDetailVo courseOrderDetail) {
        CourseOrderDetailVo detail = courseOrderService.getOrderDetailById(courseOrderDetail.getOrderDetailId());
        detail.setCourseFeedback(courseOrderDetail.getCourseFeedback());
        detail.setCourseRank(courseOrderDetail.getCourseRank());
        detail.setUpdateTime(courseOrderDetail.getUpdateTime());
        courseOrderService.newFeedback(detail);
        Integer courseId = courseOrderDetail.getCourseId();
        CourseVo courseVo = courseService.getCourseById(courseId);
        Integer rank = courseVo.getCourseTotalRank() + courseOrderDetail.getCourseRank();
        Integer totalEvaluate = courseVo.getCourseTotalEvaluate() + 1;
        courseVo.setCourseTotalRank(rank);
        courseVo.setCourseTotalEvaluate(totalEvaluate);
        courseService.updateCourse(courseId, courseVo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/feedback/{id}")
    public ResponseEntity<Void> updateFeedback(@PathVariable Integer id, @RequestBody @Valid CourseOrderDetailVo courseOrderDetail) {
        Integer oldRank = courseOrderService.getOrderDetailById(id).getCourseRank();
        Integer newRank = courseOrderDetail.getCourseRank() - oldRank;
        Integer courseId = courseOrderDetail.getCourseId();
        CourseVo courseVo = courseService.getCourseById(courseId);
        Integer rank = courseVo.getCourseTotalRank() + newRank;
        courseVo.setCourseTotalRank(rank);
        courseService.updateCourse(courseId, courseVo);
        courseOrderDetail.setOrderDetailId(id);
        courseOrderService.updateFeedback(courseOrderDetail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/feedback/{id}/{courseId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id, @PathVariable Integer courseId) {
        CourseOrderDetailVo courseOrderDetail = courseOrderService.getOrderDetailById(id);
        CourseVo courseVo = courseService.getCourseById(courseId);
        Integer rank = courseVo.getCourseTotalRank() - courseOrderDetail.getCourseRank();
        Integer totalEvaluate = courseVo.getCourseTotalEvaluate() - 1;
        courseVo.setCourseTotalRank(rank);
        courseVo.setCourseTotalEvaluate(totalEvaluate);
        courseService.updateCourse(courseId, courseVo);
        courseOrderDetail.setUpdateTime(null);
        courseOrderDetail.setCourseFeedback(null);
        courseOrderDetail.setCourseRank(0);
        courseOrderService.deleteFeedback(courseOrderDetail);
        return ResponseEntity.ok().build();
    }
}
