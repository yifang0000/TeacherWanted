package com.example.teacherwanted.coupon.dao.impl;

import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.coupon.dao.CouponDao;
import com.example.teacherwanted.coupon.model.Coupon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CouponDaoImpl implements CouponDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Coupon coupon) {
        entityManager.persist(coupon);
        return 1;
    }

    @Override
    public int deleteByCouponId(Integer couponId) {
        Coupon coupon = entityManager.find(Coupon.class, couponId);
        entityManager.remove(coupon);
        return 0;
    }

    @Override
    public int updateBycouponId(Coupon coupon) {
        entityManager.merge(coupon);
        return 1;

    }




    @Override
    public Coupon selectBycouponId(Integer couponId) {
        return entityManager.find(Coupon.class, couponId);
    }


    @Override
    public List<Coupon> findAll() {
        final String hql = "FROM Coupon";
        return entityManager
                .createQuery(hql, Coupon.class)
                .getResultList();
    }



}

