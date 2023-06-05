package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.service.CourseChapterService;
import com.example.teacherwanted.course.model.vo.CourseChapterVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
public class CourseChapterController {
    @Autowired
    private CourseChapterService courseChapterService;

    @GetMapping("/chapters/{id}")
    public ResponseEntity<List<CourseChapterVo>> findByCourseId(@PathVariable Integer id) {
        List<CourseChapterVo> list = courseChapterService.findByCourseId(id);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/chapter/{id}")
    public ResponseEntity<CourseChapterVo> findById(@PathVariable Integer id) {
        CourseChapterVo chapterVo = courseChapterService.findById(id);
        if (chapterVo != null) {
            return ResponseEntity.ok(chapterVo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/chapters", consumes = "multipart/form-data")
    public ResponseEntity<?> createChapters(@RequestPart(value = "chapterFile", required = false) List<MultipartFile> chapterFiles,
                                            @RequestParam("chapterOrder") List<String> chapterOrders,
                                            @RequestParam("chapterTitle") List<String> chapterTitles,
                                            @RequestPart(value = "chapterPhoto", required = false) List<MultipartFile> chapterPhotos,
                                            @RequestParam(value = "chapterLink", required = false) List<String> chapterLinks,
                                            @RequestParam("courseId") Integer courseId) {
        List<Integer> chapterIds = new ArrayList<>();

        for (int i = 0; i < chapterOrders.size(); i++) {
            CourseChapterVo chapterVo = new CourseChapterVo();

            MultipartFile chapterFile = chapterFiles != null ? chapterFiles.get(i) : null;
            MultipartFile chapterPhoto = chapterPhotos != null ? chapterPhotos.get(i) : null;

            // 检查视频文件是否存在
            if (chapterFile != null && !chapterFile.isEmpty()) {
                // 检查文件类型
                String contentType = chapterFile.getContentType();
                if (contentType == null || !contentType.startsWith("video/")) {
                    // 不支持的视频文件类型
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                String fileName = chapterFile.getOriginalFilename();
                // 将文件名保存到数据库或进行其他处理
                chapterVo.setFileName(fileName);
            }
            if (chapterPhoto != null) {
                try {
                    // 获取图像文件的字节数组
                    byte[] photoBytes = chapterPhoto.getBytes();

                    // 将字节数组转换为 Base64 格式的字符串
                    String base64Photo = Base64.getEncoder().encodeToString(photoBytes);

                    // 将 Base64 字符串转换为字节数组
                    byte[] base64Bytes = Base64.getDecoder().decode(base64Photo);

                    // 将转换后的字节数组设置到 ChapterRequest 对象的 chapterPhoto 字段中
                    chapterVo.setChapterPhoto(base64Bytes);
                } catch (IOException e) {
                    // 处理文件读取错误
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }

            if (chapterLinks != null) {
                chapterVo.setChapterLink(chapterLinks.get(i));
            }
            chapterVo.setChapterOrder(Integer.valueOf(chapterOrders.get(i)));
            chapterVo.setChapterTitle(chapterTitles.get(i));
            chapterVo.setCourseId(courseId);
            try {
                if (chapterFile != null) {
                    chapterVo.setChapterFile(chapterFile.getBytes());
                }
            } catch (IOException e) {
                // 处理文件读取错误
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            Integer chapterId = courseChapterService.createChapters(chapterVo);
            chapterVo.setChapterId(chapterId);
            chapterIds.add(chapterId);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(chapterIds);
    }


    //    @PostMapping("/your-endpoint-url")
//    public String handleRequest(
//            @RequestPart("file") MultipartFile file,
//            @ModelAttribute Active active
//    ) {
//        int id = active.getTeaId();
//        String folderPath = "src/main/resources/static/img/active/" + id + "/"; // 指定存儲圖片的相對路徑
//        System.out.println(id);
//
//        // 檢查資料夾是否存在，不存在則創建
//        File folder = new File(folderPath);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//
//        // 生成唯一的圖片檔名
//        String originalFilename = file.getOriginalFilename();
//        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
//        String filePath = folderPath + uniqueFileName;
//
//        try {
//            // 將檔案保存到指定路徑
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(filePath);
//            Files.write(path, bytes);
//
//            // 返回回應，包括圖片的路徑和檔名，用於存入資料庫
//            return "檔案已成功上傳，保存路徑為：" + filePath + "，檔名為：" + uniqueFileName;
//        } catch (IOException e) {
//            e.printStackTrace();
//            // 返回錯誤回應（這只是一個示例）
//            return "檔案上傳失敗";
//        }
//    }
    @PutMapping("/chapter_details/{courseId}")
    public ResponseEntity<List<CourseChapterVo>> updateChapters(@PathVariable Integer courseId,
                                                         @RequestBody @Valid List<CourseChapterVo> chapters) {
        List<CourseChapterVo> existingChapters = courseChapterService.findByCourseId(courseId);
        boolean foundMatch = false;
        for (CourseChapterVo updatedChapter : chapters) {
            Integer updatedChapterOrder = updatedChapter.getChapterOrder();
            updatedChapter.setCourseId(courseId);
            foundMatch = false; // 初始化标记为未找到匹配的章节

            for (CourseChapterVo existingChapter : existingChapters) {
                if (existingChapter.getChapterOrder().equals(updatedChapterOrder)) {
                    Integer chapterId = existingChapter.getChapterId(); // 获取章节的主键（PK）

                    // 使用章节的主键进行更新操作
                    courseChapterService.updateChapter(chapterId, updatedChapter);

                    break; // 找到匹配的章节后，可以选择中断内部循环
                }
            }
            if (!foundMatch) {
                // 在内部循环中未找到匹配的章节，执行另一个方法
                courseChapterService.createChapter(updatedChapter);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(chapters);
    }

    @PutMapping("/chapters/{courseId}")
    public ResponseEntity<CourseChapterVo> updateChapter(@PathVariable Integer courseId,
                                                         @RequestBody @Valid CourseChapterVo chapterVo) {
        if (chapterVo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            courseChapterService.updateChapter(courseId, chapterVo);
            return ResponseEntity.status(HttpStatus.OK).body(chapterVo);
        }
    }

    @DeleteMapping("/chapters/{courseId}")
    public ResponseEntity<Object> deleteChaptersById(@PathVariable Integer courseId) {
        Integer deletedCount = courseChapterService.deleteChaptersById(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
    }
}
