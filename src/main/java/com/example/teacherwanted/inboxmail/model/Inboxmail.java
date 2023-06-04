package com.example.teacherwanted.inboxmail.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

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
    @Basic
    @Column(name = "sender")
    private int sender;
    @Basic
    @Column(name = "receiver")
    private int receiver;
    @Basic
    @Column(name = "mail_title")
    private String mailTitle;
    @Basic
    @Column(name = "mail_content")
    private String mailContent;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "mail_status")
    private int mailStatus;
    @Basic
    @Column(name = "mail_read_status")
    private int mailReadStatus;

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(int mailStatus) {
        this.mailStatus = mailStatus;
    }

    public int getMailReadStatus() {
        return mailReadStatus;
    }

    public void setMailReadStatus(int mailReadStatus) {
        this.mailReadStatus = mailReadStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inboxmail that = (Inboxmail) o;
        return mailId == that.mailId && sender == that.sender && receiver == that.receiver && mailStatus == that.mailStatus && mailReadStatus == that.mailReadStatus && Objects.equals(mailTitle, that.mailTitle) && Objects.equals(mailContent, that.mailContent) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailId, sender, receiver, mailTitle, mailContent, createTime, mailStatus, mailReadStatus);
    }
}
