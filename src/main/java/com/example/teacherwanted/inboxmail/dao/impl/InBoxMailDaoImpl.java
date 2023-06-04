package com.example.teacherwanted.inboxmail.dao.impl;

import com.example.teacherwanted.inboxmail.dao.InBoxMailDao;
import com.example.teacherwanted.inboxmail.model.Inboxmail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InBoxMailDaoImpl implements InBoxMailDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Inboxmail inboxmail) {
        entityManager.persist(inboxmail);
        return 0;
    }

    @Override
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
    public List<Inboxmail> selectAll() {
        return null;
    }
}
