package com.example.teacherwanted.administrator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@ToString
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private Integer teaId;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "tea_profile")
    private String teaProfile;

    @Column(name = "tea_photo")
    private byte[] teaPhoto;

    @Column(name = "tea_location")
    private String teaLocation;

    @Column(name = "subscribe_number")
    private Integer subscribeNumber;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "teaching_subject1")
    private Integer teachingSubject1;

    @Column(name = "teaching_subject2")
    private Integer teachingSubject2;

    @Column(name = "teaching_subject3")
    private Integer teachingSubject3;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    @Column(name = "tea_name")
    private String teaName;
}

