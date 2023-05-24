package com.example.teacherwanted.active.controller;

import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.active.service.ActiveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
public class ActiveController {

    @Autowired
    private ActiveService activeService;


    //    前臺操作 開始
    //    單個活動
    @GetMapping("/active")
    public ResponseEntity<?> selectByIdActive(@RequestParam Integer activityId) {
        //  System.out.println(activityId);
        Active active = activeService.selectById(activityId);
        //  判斷狀態
        if (active != null) {
            return ResponseEntity.ok(active);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("查無此活動");
        }

    }

    //    推薦活動
    @GetMapping("/activeRecommend")
    public List<Active> recommendActivities(@RequestParam(required = false) String activityType) {
        System.out.println(activityType);


        return activeService.recommendActivities(activityType);
    }
    //    前臺操作 結束

    //    後臺操作 開始


    @GetMapping("/activeBack")
    public List<Active> selectAllActiveBack(@RequestParam(required = false) String searchKeyword,
                                            @RequestParam(required = false) String activityType,
                                            @SessionAttribute(value = "TeacherSession") Integer teaId) {
        System.out.println(teaId);

        List<Active> activeList = activeService.selectBackAll(searchKeyword, activityType, teaId);
        return activeList;
    }

    @PostMapping("/activeBackAdd")
    public String insertActiveBack(@RequestBody Active active,
                                   @SessionAttribute("TeacherSession") Integer teaId) {
        active.setTeaId(teaId);
        return activeService.insert(active);
    }

    @GetMapping("/activeBackEdit")
    public Active selectByIdActiveBack(@RequestParam Integer activityId) {
        //  System.out.println(activityId);
        Active activeEdit = activeService.selectBackById(activityId);
        return activeEdit;
    }

    @PutMapping("/activeBackEdit")
    public String updateActiveBack(@RequestBody Active active,
                                   @SessionAttribute("TeacherSession") Integer teaId) {
        active.setTeaId(teaId);
        return activeService.update(active);
    }

    @PutMapping("/activeBackStatusEdit")
    public String updateActiveBackStatus(@RequestBody Active active) {
//        return activeService.update(active);
        System.out.println("activeBackStatusEdit:" + active.getActivityId());
        System.out.println("activeBackStatusEdit:" + active.getActivityStatus());

        return activeService.updateStatus(active, active.getActivityStatus());

    }

    @DeleteMapping("/activeBackDelete")
    public String deleteActiveBack(@RequestParam Integer activityId) {
        return activeService.deleteById(activityId);
    }

    //    後臺操作 結束

    //    以下是測試用

    //    會員登入狀態

    @PostMapping("/activeLogin")
    public String easyLogin(@RequestBody Integer memberId, HttpSession session) {
        session.setAttribute("MemberId", memberId);
        return "會員登入成功";
    }

    @PostMapping("/activeBackLogin")
    public String easyBackLogin(@RequestBody Integer teaId, HttpSession session) {
        session.setAttribute("TeacherSession", teaId);
        return "管理員登入成功";
    }

    @GetMapping("/activeAllLogout")
    public String easyLogout(HttpSession session) {
        session.removeAttribute("MemberId");
        session.removeAttribute("TeacherSession");
        return "全部都以登出";
    }

    //    上傳，直接把圖片上傳至檔案夾
    @PostMapping("/your-endpoint-url")
    public String handleRequest(
            @RequestPart("file") MultipartFile file,
            @ModelAttribute Active active
    ) {
        int id = active.getTeaId();
        String folderPath = "src/main/resources/static/img/active/" + id + "/"; // 指定存儲圖片的相對路徑
        System.out.println(id);

        // 檢查資料夾是否存在，不存在則創建
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 生成唯一的圖片檔名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        String filePath = folderPath + uniqueFileName;

        try {
            // 將檔案保存到指定路徑
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            // 返回回應，包括圖片的路徑和檔名，用於存入資料庫
            return "檔案已成功上傳，保存路徑為：" + filePath + "，檔名為：" + uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            // 返回錯誤回應（這只是一個示例）
            return "檔案上傳失敗";
        }
    }
}


