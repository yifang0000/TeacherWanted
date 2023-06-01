package com.example.teacherwanted.member.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "MEMBER")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private static final long serialVersionUID = 1062017122345367218L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id", nullable = false) //會員編號
    private Integer memId;

    @Column(name = "mem_account", nullable = false) //會員帳號
    private String memAccount;

    @Column(name = "mem_password", nullable = false) //會員密碼
    private String memPassword;

    @Column(name = "mem_name", nullable = false)  //會員姓名
    private String memName;

    @Column(name = "mem_phone", nullable = false)  //會員電話
    private String memPhone;

    @Column(name = "mem_nickname", nullable = false) //會員暱稱
    private String memNickname;

    @Column(name = "mem_birthday", nullable = false) //會員生日
    private Timestamp memBirthday;

    @Column(name = "mem_gender", nullable = false)  //姓別
    private Integer memGender;

    @Column(name = "mem_email")  //信箱
    private String memEmail;

    @Column(name = "mail_verify", nullable = false) //信箱認證
    private Integer mailVerify;

    @Column(name = "mem_location", nullable = false) //地區
    private String memLocation;

    @Column(name = "mem_photo")  //大頭貼
    private byte[] memPhoto;

    @Column(name = "interest1", nullable = false)  //有興趣主題1
    private Integer interest1;

    @Column(name = "interest2", nullable = false)  //有興趣主題2
    private Integer interest2;

    @Column(name = "interest3", nullable = false)  //有興趣主題3
    private Integer interest3;

    @Column(name = "create_time", nullable = false) //會員註冊日期
    private Timestamp createTime;

    @Column(name = "update_time", nullable = false) //會員狀態更新日期
    private Timestamp updateTime;

    @Column(name = "mem_status", nullable = false) //會員狀態  是否有被停權
    private Integer memStatus;
}
