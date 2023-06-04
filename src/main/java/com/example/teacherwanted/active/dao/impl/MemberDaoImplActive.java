package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.MemberDaoActive;
import com.example.teacherwanted.active.dao.MemberDaoActive;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.active.model.MemberActive;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImplActive implements MemberDaoActive {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(MemberActive pojo) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }


    @Override
    public int update(MemberActive pojo) {
        return 0;
    }

    @Override
    public MemberActive selectById(Integer id) {
        return entityManager.find(MemberActive.class, id);
    }

    @Override
    public List<MemberActive> selectAll() {
        return null;
    }





}
