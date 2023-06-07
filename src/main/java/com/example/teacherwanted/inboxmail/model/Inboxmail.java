package com.example.teacherwanted.inboxmail.model;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@ToString
@Entity
@Table(name = "INTERNAL_MESSAGE", schema = "teacher_wanted", catalog = "")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Inboxmail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mail_id")
    private int mailId;

    @Column(name = "sender")
    private int sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "mail_title")
    private String mailTitle;

    @Column(name = "mail_content")
    private String mailContent;

    @Column(name = "mail_status")
    private int mailStatus;

    @Column(name = "mail_read_status")
    private int mailReadStatus;

    @Column(name = "mail_create_date")
    private Date mailCreateDate;


    @Column(name = "sender_name")
    private String senderName;
}
