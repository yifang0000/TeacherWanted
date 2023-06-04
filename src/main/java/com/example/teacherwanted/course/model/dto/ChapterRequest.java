package com.example.teacherwanted.course.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ChapterRequest {
    private String chapterOrder;
    private String chapterTitle;
    private MultipartFile chapterPhoto;
    private String chapterLink;
    private MultipartFile chapterFile;


}
