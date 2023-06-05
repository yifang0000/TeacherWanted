package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "FAVORITE_TEACHER")
public class FavoriteTeacherVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_teacher_id")
    private Integer favoriteTeacherId;
    private Integer memId;
    private Integer teaId;
    private Date createTime;
}
