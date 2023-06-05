package com.example.teacherwanted.member.dao.impl;

import com.example.teacherwanted.member.dao.MemberCalendarDao;
import com.example.teacherwanted.member.model.MemberCalendar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberCalendarDaoImpl implements MemberCalendarDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public int insert(MemberCalendar memberCalendar) {
        entityManager.persist(memberCalendar);
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        MemberCalendar memberCalendar = entityManager.find(MemberCalendar.class, id);
        entityManager.remove(memberCalendar);
        return 0;
    }

    @Override
    public int update(MemberCalendar memberCalendar) {
        entityManager.merge(memberCalendar);
        return 0;
    }

    @Override
    public MemberCalendar selectById(Integer id) {
        return null;
    }

    @Override
    public List<MemberCalendar> selectAll() {
        return null;
    }

    @Override
    public List<MemberCalendar> selectByMemId(Integer memId) {
        TypedQuery<MemberCalendar> query = entityManager.createQuery(
                "FROM MemberCalendar WHERE memId = :memId",
                MemberCalendar.class);
        query.setParameter("memId", memId);


        return query.getResultList();
    }
}
