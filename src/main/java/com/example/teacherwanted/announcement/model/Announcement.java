package com.example.teacherwanted.announcement.model;
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

    @Column(name = "ann_content")
    private String annContent;

    @Column(name = "ann_date")
    private Date annDate;

    @Column(name = "ann_status")
    private Integer annStatus;

}
