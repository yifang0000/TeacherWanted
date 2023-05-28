package com.example.teacherwanted.coupon.dao.impl;

import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.coupon.dao.CouponDao;
import com.example.teacherwanted.coupon.model.Coupon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class CouponDaoImpl implements CouponDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Coupon coupon) {
        entityManager.persist(coupon);
//        todo:觀察是否為新建的id
//        System.out.println(coupon.getCouponId());

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



//  新增
    @Override
    public Coupon selectBycouponId(Integer couponId) {
        Coupon coupon = entityManager.find(Coupon.class, couponId);
        if(coupon!=null) {
            return coupon;
        }else{
            return null;
        }
    }


    @Override
    public List<Coupon> findAll() {
        final String hql = "FROM Coupon";
        List<Coupon> resultList = entityManager
                .createQuery(hql, Coupon.class)
                .getResultList();
//        System.out.println(resultList);
        return resultList;
    }



}

