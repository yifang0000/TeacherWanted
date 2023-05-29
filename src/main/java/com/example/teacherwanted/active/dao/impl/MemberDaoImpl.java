package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.MemberDao;
import com.example.teacherwanted.active.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Member pojo) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }


    @Override
    public int update(Member pojo) {
        return 0;
    }

    @Override
    public Member selectById(Integer id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    public List<Member> selectAll() {
        return null;
    }





}
