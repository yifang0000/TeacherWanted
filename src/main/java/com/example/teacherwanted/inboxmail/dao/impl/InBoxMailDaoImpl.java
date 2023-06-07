package com.example.teacherwanted.inboxmail.dao.impl;

import com.example.teacherwanted.course.model.vo.CourseVo;
import com.example.teacherwanted.inboxmail.dao.InBoxMailDao;
import com.example.teacherwanted.inboxmail.model.Inboxmail;
import com.example.teacherwanted.member.model.Member;
import jakarta.mail.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InBoxMailDaoImpl implements InBoxMailDao {
    @PersistenceContext
    private EntityManager entityManager;
    private Session session;

    @Override
    public int insert(Inboxmail inboxmail) {
        entityManager.persist(inboxmail);
        return 0;
    }

    @Override          //刪除語法 執行 回傳刪除結果
    public int deleteBymailId(Integer mailId) {
        Inboxmail inboxmail = entityManager.find(Inboxmail.class, mailId);
        entityManager.remove(inboxmail);
        return 0;
    }

    @Override
    public int updateBymailId(Integer mailId) {
        entityManager.merge(mailId);
        return 0;
    }
    @Override
    public int updateStatus(Integer mailId) {
        entityManager.merge(mailId);
        return 0;
    }

    @Override
    public Inboxmail selectBymailId(Integer mailId) {
        return entityManager.find(Inboxmail.class, mailId);
    }

    @Override
    public List<Inboxmail> selectAll(Integer mailId) {
        return null;
    }

    @Override
    public List<Inboxmail> getInboxmailByMemId(Integer memId) {
        String hql = "FROM Inboxmail im WHERE im.receiver = :memId";
        TypedQuery<Inboxmail> query = entityManager.createQuery(hql, Inboxmail.class);
        query.setParameter("memId", memId);
        return query.getResultList();
    }

    @Override
    public Integer updateStatusById(Integer mailId, Integer status) {
        Inboxmail inboxmail = entityManager.find(Inboxmail.class, mailId);
        inboxmail.setMailStatus(status);
        entityManager.merge(inboxmail);
        return status;
    }

}
