package com.example.teacherwanted.member.dao.impl;

import com.example.teacherwanted.member.dao.MemberDao;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.wish.entity.Wish;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private EntityManager entityManager;

    //會員資料表的功能如下:修改, 用id查詢,(查詢全部不確定需不需要)

    @Override
    public int insert(Member member) {
        entityManager.persist(member);
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
        Member member = entityManager.find(Member.class, id);
        entityManager.remove(member);
        return 0;
    }

    @Override
    public int update(Member member) {
        entityManager.merge(member);
        return 1;
    }

    @Override
    public Member selectById(Integer memId) {

        return entityManager.find(Member.class, memId);
    }

    @Override
    public List<Wish> getWishByMemId(Integer memId) {
        String hql = "FROM Wish ws WHERE ws.memId = :memId";
        TypedQuery<Wish> query = entityManager.createQuery(hql, Wish.class);
        query.setParameter("memId", memId);
        return query.getResultList();
    }

    @Override
    public List<Member> selectAll() {
        return null;
    };



//    @Override
//    public List<Member> selectAll() {
//        final String hql = "FROM Member ORDER BY memId";
//        return entityManager
//                .createQuery(hql,Member.class)
//                .getResultList();
//    }


}
