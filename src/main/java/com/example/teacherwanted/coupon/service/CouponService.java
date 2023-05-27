package com.example.teacherwanted.coupon.service;

import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.coupon.model.Coupon;

import java.util.List;

public interface CouponService {
    List<Coupon> findAll();

    Coupon selectBycouponId(Integer couponId) ;

    int insert(Coupon coupon);

    int updateBycouponId(Coupon coupon);

    int deleteByCouponId(Integer couponId);

}
