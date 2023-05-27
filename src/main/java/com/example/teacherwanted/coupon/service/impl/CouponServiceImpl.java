package com.example.teacherwanted.coupon.service.impl;

import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.coupon.dao.CouponDao;
import com.example.teacherwanted.coupon.model.Coupon;
import com.example.teacherwanted.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    public CouponDao couponDao;

    @Override
    public List<Coupon> findAll() {
            return couponDao.findAll();
    }

    @Override
    public Coupon selectBycouponId(Integer couponId) {

        return couponDao.selectBycouponId(couponId);
    }

    @Override
    public int insert(Coupon coupon) {
        return couponDao.insert(coupon);
    }

    @Override
    public int updateBycouponId(Coupon coupon) {
        return couponDao.updateBycouponId(coupon);
    }

    @Override
    public int deleteByCouponId(Integer couponId) {
        return couponDao.deleteByCouponId(couponId);
    }
}
