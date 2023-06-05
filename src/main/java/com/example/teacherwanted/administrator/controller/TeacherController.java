package com.example.teacherwanted.administrator.controller;

import com.example.teacherwanted.administrator.model.Teacher;
import com.example.teacherwanted.administrator.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {
    @Autowired
    public TeacherService teacherService;

//    //    查詢全部：[restful設計]無論查詢列表有無資料，都需要回傳200給前端
//    @GetMapping("/announcements")
//    public ResponseEntity<List<Teacher>> findAll() {
//        List<Teacher> all = teacherService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(all);
//    }

    //    查詢單個：[restful設計]：若該資料查詢不到，須回傳404給前端
    @GetMapping("/teachers/pro/{adminId}")
    public ResponseEntity<Teacher> selectByAdminId(@PathVariable Integer adminId) {
        Teacher teacher = teacherService.selectByAdminId(adminId);
        if (teacher != null) {
            return ResponseEntity.status(HttpStatus.OK).body(teacher);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    新增 ok
    @PostMapping("/teachers")
    public ResponseEntity<?> insert(@RequestBody Teacher teacher) {
        teacherService.insert(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //    修改
    @PutMapping("/teachers/pro/{adminId}")
    public ResponseEntity<?> updateByAnnId(@RequestBody Teacher teacher) {
//        System.out.println(teacher);
        teacherService.updateByAdminId(teacher);
//        Coupon updatecoupon = couponService.selectBycouponId(coupon.getCouponId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //    刪除
    @DeleteMapping("/teachers/{adminId}")
    public ResponseEntity<?> deleteByAdminId(@PathVariable Integer adminId) {
        teacherService.deleteByAdminId(adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
