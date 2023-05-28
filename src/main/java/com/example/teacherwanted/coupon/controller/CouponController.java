package com.example.teacherwanted.coupon.controller;

import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.coupon.model.Coupon;
import com.example.teacherwanted.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;
//    查詢全部：[restful設計]無論查詢列表有無資料，都需要回傳200給前端
    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> findAll(){
        List<Coupon> couponList = couponService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(couponList);
    }

//    查詢單個：[restful設計]：若該資料查詢不到，須回傳404給前端
    @GetMapping("/coupons/{couponId}")
    public ResponseEntity<Coupon> selectBycouponId(@PathVariable Integer couponId){
        Coupon coupon = couponService.selectBycouponId(couponId);
        if(coupon != null){
            return ResponseEntity.status(HttpStatus.OK).body(coupon);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    新增 ok
    @PostMapping("/coupons")
    public ResponseEntity<Coupon> insert(@RequestBody Coupon coupon){
        couponService.insert(coupon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//    修改
    @PutMapping("/coupons/{couponId}")
    public ResponseEntity<?> updateBycouponId(@RequestBody Coupon coupon){
        System.out.println(coupon);
        couponService.updateBycouponId(coupon);
//        Coupon updatecoupon = couponService.selectBycouponId(coupon.getCouponId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }



//    刪除
    @DeleteMapping("/coupons/{couponId}")
    public ResponseEntity<?> deleteByCouponId(@PathVariable Integer couponId) {
        couponService.deleteByCouponId(couponId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
