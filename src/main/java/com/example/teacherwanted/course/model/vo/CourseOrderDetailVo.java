package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "COURSE_ORDER_DETAIL")
public class CourseOrderDetailVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    Integer orderDetailId;
    Integer orderId;
    Integer courseId;
    Integer courseRank;
    String courseFeedback;
    Date updateTime;
    Integer memId;

}
