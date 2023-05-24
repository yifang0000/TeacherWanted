package com.example.teacherwanted.active.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
@ToString
@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private static final long serialVersionUID = 1062013212345367218L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id", nullable = false)
    private Integer memId; // 會員編號

    @Column(name = "mem_account", nullable = false)
    private String memAccount; // 帳號

    @Column(name = "mem_password", nullable = false)
    private String memPassword; // 密碼

    @Column(name = "mem_name", nullable = false)
    private String memName; // 姓名

    @Column(name = "mem_phone", nullable = false)
    private String memPhone; // 電話

    @Column(name = "mem_nickname", nullable = false)
    private String memNickname; // 暱稱

    @Column(name = "mem_birthday", nullable = false)
    private Timestamp memBirthday; // 生日

    @Column(name = "mem_gender", nullable = false)
    private Integer memGender; // 性別

    @Column(name = "mem_email")
    private String memEmail; // 信箱

    @Column(name = "mail_verify", nullable = false)
    private Integer mailVerify; // 信箱認證是否通過

    @Column(name = "mem_location", nullable = false)
    private String memLocation; // 地區

    @Column(name = "mem_photo")
    private byte[] memPhoto; // 大頭貼

    @Column(name = "interest1", nullable = false)
    private Integer interest1; // 有興趣主題1

    @Column(name = "interest2", nullable = false)
    private Integer interest2; // 有興趣主題2

    @Column(name = "interest3", nullable = false)
    private Integer interest3; // 有興趣主題3

    @Column(name = "create_time", nullable = false)
    private Timestamp createTime; // 新增日期

    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime; // 更新日期

    @Column(name = "mem_status", nullable = false)
    private Integer memStatus; // 會員狀態

    // 此處省略 get 和 set 方法、建構子等。
}