package com.example.teacherwanted.active.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "COURSE")
public class CourseVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    Integer courseId;
    @Column(name = "course_name")
    String courseName;
    @Column(name = "course_category_id")
    Integer courseCategoryId;
    @Column(name = "course_detail")
    String courseDetail;
    @Column(name = "course_price")
    Integer coursePrice;
    @Column(name = "course_length")
    Double courseLength;
    @Column(name = "cooling_off_period")
    Integer coolingOffPeriod;
    @Column(name = "tea_id")
    Integer teaId;
    @Column(name = "course_total_rank")
    Integer courseTotalRank;
    @Column(name = "course_total_evaluate")
    Integer courseTotalEvaluate;
    @Column(name = "bought_count")
    Integer boughtCount;
    @Column(name = "course_remarks")
    String courseRemarks;
    @Column(name = "course_status")
    Integer courseStatus;
    @Column(name = "create_time")
    Date createTime;
    @Column(name = "update_time")
    Date updateTime;
    @Column(name = "course_photo")
    byte[] coursePhoto;


}
