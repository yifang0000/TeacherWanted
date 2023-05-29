package com.example.teacherwanted.administrator.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@ToString
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    @Id
//	自動編號
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private Integer adminId;
    @Column(name = "admin_account")
    private String adminAccount;
    @Column(name = "admin_password")
    private String adminPassword;
    @Column(name = "admin_name")
    private String adminName;
// todo:增加email驗證
    @Column(name ="admin_email")
    private String adminEmail;
    @Column(name="admin_phone")
    private String adminPhone;
    @Column(name="permission_id")
    private Integer permissionId;
    @Column(name="admin_status")
    private Integer adminStatus;

    //, insertable = false?
    @Column(name = "CREATED_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdDate;

    @Column(name = "LAST_UPDATED_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp lastUpdatedDate;
}
