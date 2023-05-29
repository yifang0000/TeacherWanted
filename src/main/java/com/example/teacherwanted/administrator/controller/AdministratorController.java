package com.example.teacherwanted.administrator.controller;

import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.administrator.service.AdministratorService;
import com.example.teacherwanted.administrator.service.impl.AdministratorServiceImpl;
import com.example.teacherwanted.coupon.model.Coupon;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdministratorController {
    private final static Logger log = LoggerFactory.getLogger(AdministratorController.class);
    @Autowired
    private AdministratorService administratorService;


    //    查詢全部：[restful設計]無論查詢列表有無資料，都需要回傳200給前端
//    @SessionAttribute("adminSession") Administrator administrator1
    @GetMapping("/administrators")
    public ResponseEntity<List<Administrator>> findAll() {
        List<Administrator> administratorList = administratorService.findAll();
//        System.out.println(administrator1.getAdminName()+"在操作使用者");
        return ResponseEntity.status(HttpStatus.OK).body(administratorList);
    }
    //    新增使用者
    @PostMapping("/administrators/insert")
    public ResponseEntity<?> insert(@RequestBody Administrator administrator,@SessionAttribute("adminSession") Administrator administrator1) {
        administratorService.insert(administrator);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //    查詢單個：[restful設計]：若該資料查詢不到，須回傳404給前端
    @GetMapping("/administrators/{adminId}")
    public ResponseEntity<Administrator> selectByAdminId(@PathVariable Integer adminId) {
        Administrator administrator = administratorService.selectByAdminId(adminId);
        if (administrator != null) {
            return ResponseEntity.status(HttpStatus.OK).body(administrator);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    //    修改
    @PutMapping("/administrators/{adminId}")
    public ResponseEntity<?> updateByAdminId(@RequestBody Administrator administrator) {
        System.out.println(administrator);
        administratorService.updateByAdminId(administrator);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //    刪除
    @DeleteMapping("/administrators/{adminId}")
    public ResponseEntity<?> deleteByAdminId(@PathVariable Integer adminId) {
        administratorService.deleteByAdminId(adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    登入=>儲存session =>js hidden
    @PostMapping("/administrators/login")
    public ResponseEntity<Administrator> login(@RequestBody Administrator administrator,
                                               HttpSession session){
        Administrator administrator1 = administratorService.login(administrator);
        session.setAttribute("adminSession", administrator1);
        log.info(administrator1.getAdminName() + "登入ㄌ");
        return ResponseEntity.status(HttpStatus.OK).body(administrator1);
    }

    //用來獲取session
    @GetMapping("/administrators/session")
    public ResponseEntity<Administrator> getSessionInfo(@SessionAttribute("adminSession") Administrator administrator1) {

        return ResponseEntity.status(HttpStatus.OK).body(administrator1);
    }
//    登出

}
