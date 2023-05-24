package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.MemberDao;
import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.active.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Member pojo) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int update(Member pojo) {
        return 0;
    }

    @Override
    public Member selectById(String id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    public List<Member> selectBackAll(Integer key, Integer type, Integer id) {
        return null;
    }
}
