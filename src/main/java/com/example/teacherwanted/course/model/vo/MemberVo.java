package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "MEMBER")
public class MemberVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Integer memId;
    @NotNull
    @Column(name = "mem_account")
    @NotBlank
    private String memAccount;
    @NotBlank
    @Column(name = "mem_password")
    private String memPassword;
    @NotNull
    @Column(name = "mem_name")
    private String memName;
    @NotNull
    @Column(name = "mem_phone")
    private String memPhone;
    @NotNull
    @Column(name = "mem_nickname")
    private String memNickname;
    @NotNull
    @Column(name = "mem_birthday")
    private Date memBirthday;
    @NotNull
    @Column(name = "mem_gender")
    private Integer memGender;
    @NotBlank
    @Column(name = "mem_email")
    private String memEmail;
    @Column(name = "mail_verify")
    private Integer mailVerify;
    @NotNull
    @Column(name = "mem_location")
    private String memLocation;
    @Column(name = "mem_photo")
    private byte[] memPhoto;
    @NotNull
    @Column(name = "interest1")
    private Integer interest1;
    @NotNull
    @Column(name = "interest2")
    private Integer interest2;
    @NotNull
    @Column(name = "interest3")
    private Integer interest3;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "mem_status")
    private Integer memStatus;
}
