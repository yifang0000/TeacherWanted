package com.example.teacherwanted.administrator.controller;

import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.administrator.service.AdministratorService;
import com.example.teacherwanted.coupon.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
//    新增使用者
    @PostMapping("/administrators/insert")
    public ResponseEntity<?> insert(@RequestBody Administrator administrator){
        administratorService.insert(administrator);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //    查詢全部：[restful設計]無論查詢列表有無資料，都需要回傳200給前端
@GetMapping("/administrators")
public ResponseEntity<List<Administrator>> findAll(){
    List<Administrator> administratorList = administratorService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(administratorList);
}

    //    查詢單個：[restful設計]：若該資料查詢不到，須回傳404給前端
    @GetMapping("/administrators/{adminId}")
    public ResponseEntity<Administrator> selectByAdminId(@PathVariable Integer adminId){
        Administrator administrator = administratorService.selectByAdminId(adminId);
        if(administrator != null){
            return ResponseEntity.status(HttpStatus.OK).body(administrator);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
//    修改
@PutMapping("/administrators/{adminId}")
public ResponseEntity<?> updateByAdminId(@RequestBody Administrator administrator){
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
//    登出

}
