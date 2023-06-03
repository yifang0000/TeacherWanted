package com.example.teacherwanted.announcement.controller;

import com.example.teacherwanted.announcement.model.Announcement;
import com.example.teacherwanted.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncementController {
    @Autowired
    public AnnouncementService announcementService;

    //    原版查詢全部：[restful設計]無論查詢列表有無資料，都需要回傳200給前端
//    @GetMapping("/announcements")
//    public ResponseEntity<List<Announcement>> findAll() {
//        List<Announcement> all = announcementService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(all);
//    }


    //    查詢全部：[restful設計]無論查詢列表有無資料，都需要回傳200給前端
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> findAll(@RequestParam(required = false) String annCategory,
                                                      @RequestParam(required = false) boolean front) {
        List<Announcement> all = announcementService.findAll(annCategory,front);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
    //    查詢單個：[restful設計]：若該資料查詢不到，須回傳404給前端
    @GetMapping("/announcements/{annId}")
    public ResponseEntity<Announcement> selectByAnnId(@PathVariable Integer annId) {
        Announcement announcement = announcementService.selectByAnnId(annId);
        if (announcement != null) {
            return ResponseEntity.status(HttpStatus.OK).body(announcement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    新增 ok
    @PostMapping("/announcements")
    public ResponseEntity<?> insert(@RequestBody Announcement announcement) {
        int adminId = announcementService.insert(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminId);
    }

    //    修改
    @PutMapping("/announcements/{annId}")
    public ResponseEntity<?> updateByAnnId(@RequestBody Announcement announcement) {
        System.out.println(announcement);
        announcementService.updateByAnnId(announcement);
//        Coupon updatecoupon = couponService.selectBycouponId(coupon.getCouponId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //    刪除
    @DeleteMapping("/announcements/{annId}")
    public ResponseEntity<?> deleteByAnnId(@PathVariable Integer annId) {
        announcementService.deleteByAnnId(annId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
