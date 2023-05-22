package com.example.teacherwanted.active.model;

import com.example.teacherwanted.active.utils.ActivityDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "ACTIVITY")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Active {
    private static final long serialVersionUID = 1062017122345367218L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Integer activityId;  // 活動代碼

    @Column(name = "tea_id")
    private Integer teaId;  // 老師編號

    @Column(name = "activity_type")
    private String activityType;  // 活動類別

    @Column(name = "current_number")
    private Integer currentNumber;  // 目前人數

    @Column(name = "max_number")
    private String maxNumber;  // 上限人數

    @Column(name = "activity_detail")
    private String activityDetail;  // 活動介紹

    @Column(name = "activity_price")
    private Integer activityPrice;  // 活動價格

    @Column(name = "activity_location")
    private String activityLocation;  // 活動地點

    @Column(name = "activity_start_time")
    private Timestamp activityStartTime;  // 活動開始時間

    @Column(name = "activity_end_time")
    private Timestamp activityEndTime;  // 活動結束時間

    @Column(name = "activity_due_time")
    private Timestamp activityDueTime;  // 活動報名截止時間

    @Column(name = "create_time")
    private Timestamp createTime;  // 新增日期

    @Column(name = "update_time")
    private Timestamp updateTime;  // 更新日期

    @Column(name = "remarks")
    private String remarks;  // 活動審核評語

    @Column(name = "activity_status")
    private Integer activityStatus;  // 活動狀態

    //    @JsonProperty("activityPhoto")
//    @JsonDeserialize(using = ActivityDeserializer.class)
    @Column(name = "activity_photo")
    private byte[] activityPhoto;  // 活動圖片

    @Column(name = "activity_name")
    private String activityName;  // 活動名稱

    @Column(name = "activity_lat")
    private Double activityLat;

    @Column(name = "activity_lng")
    private Double activityLng;

    @Column(name = "activity_photo_type")
    private String activityPhotoType;
}