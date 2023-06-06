package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "COURSE_ORDER")
public class CourseOrderVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderId;
    Integer memId;
    Integer originalPrice;
    String couponCode;
    Integer discount;
    Integer discountPrice;
    Integer orderStatus;
}
