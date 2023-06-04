package com.example.teacherwanted.course.model.vo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "COURSE_CHAPTER")
public class CourseChapterVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private Integer chapterId;
    @Column(name = "course_id")
    private Integer courseId;
    private Integer chapterOrder;
    @Lob
    private byte[] chapterPhoto;
    @Lob
    private byte[] chapterFile;
    private String chapterLink;
    private String chapterTitle;
    private Date createTime;
    private Date updateTime;
    private Integer chapterStatus;
    private String fileName;


}
