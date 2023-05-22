package com.example.teacherwanted.coupon.dao;

import com.example.teacherwanted.coupon.model.Coupon;

import java.util.List;

public interface CouponDao {
    int insert(Coupon coupon);

	int deleteByCouponId(Integer couponId);


    int updateBycouponId(Coupon coupon);

//    int offcoupon(Coupon coupon);

    Coupon selectBycouponId(Integer couponId) ;

    List<Coupon> findAll();
}

