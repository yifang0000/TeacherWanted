package com.example.teacherwanted.announcement.model;
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
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ann_id")
    private Integer annId;
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "ann_title")
    private String annTitle;
    @Column(name = "ann_category")
    private String annCategory;

    @Column(name = "ann_content")
    private String annContent;

    @Column(name = "ann_date")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp annDate;

    @Column(name = "ann_status")
    private Integer annStatus;

}
