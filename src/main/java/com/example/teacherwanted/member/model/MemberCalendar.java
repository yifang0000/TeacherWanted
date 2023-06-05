package com.example.teacherwanted.member.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "MEMBER_CALENDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calender_id")
    private Integer calendarId;

    @Column(name = "calender_start_time")
    private Timestamp calendarStartTime;

    @Column(name = "calender_end_time")
    private Timestamp calendarEndTime;

    @Column(name = "calender_name")
    private String calendarName;

    @Column(name = "calender_content")
    private String calendarContent;

    @Column(name = "calender_color")
    private String calendarColor;

    @Column(name = "mem_id")
    private Integer memId;

    // 省略建構子以及get和set方法
}
