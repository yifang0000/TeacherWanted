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
}

