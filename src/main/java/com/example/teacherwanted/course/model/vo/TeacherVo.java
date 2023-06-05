package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "TEACHER")
public class TeacherVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private Integer teaId;
    @NotNull
    @Column(name = "admin_id")
    private Integer adminId;
    @Column(name = "tea_profile")
    private String teaProfile;
    @Column(name = "tea_photo")
    private byte[] teaPhoto;
    @NotNull
    @Column(name = "tea_location")
    private String teaLocation;
    @NotNull
    @Column(name = "subscribe_number")
    private Integer subscribeNumber;
    @NotNull
    @Column(name = "balance")
    private Integer balance;
    @Column(name = "teaching_subject1")
    private Integer teachingSubject1;
    @Column(name = "teaching_subject2")
    private Integer teachingSubject2;
    @Column(name = "teaching_subject3")
    private Integer teachingSubject3;
    @NotNull
    @Column(name = "bank_code")
    private String bankCode;
    @NotNull
    @Column(name = "bank_account")
    private String bankAccount;
    @NotNull
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @NotNull
    @Column(name = "tea_name")
    private String teaName;
}
