package com.example.teacherwanted.active.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "FAVORITE_ACTIVITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_activity_id")
    public Integer favoriteActivityId;

    @Column(name = "mem_id")
    public Integer memId;

    @Column(name = "activity_id")
    public Integer activityId;

    @Column(name = "create_time")
    public Timestamp createTime;

    @Column(name = "activity_name")
    private String activityName;  // 活動名稱

    @Column(name = "activity_photo")
    private byte[] activityPhoto;  // 活動圖片

    @Column(name = "activity_photo_type")
    private String activityPhotoType;
}

