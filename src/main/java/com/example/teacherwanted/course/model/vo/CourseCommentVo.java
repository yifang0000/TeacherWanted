package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "COURSE_COMMENT")
public class CourseCommentVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_comment_id")
    private Integer courseCommentId;
    private Integer courseId;
    private Integer memId;
    private String commentTitle;
    private String courseCommentContext;
    private Date createTime;
    private Date updateTime;
    private Integer commentStatus;
}
