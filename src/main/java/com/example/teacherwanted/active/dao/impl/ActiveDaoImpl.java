package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.model.Active;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ActiveDaoImpl implements ActiveDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public int insert(Active active) {
        entityManager.persist(active);
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
        Active active = entityManager.find(Active.class, id);
        entityManager.remove(active);
        return 0;
    }

    @Override
    public int update(Active active) {
        entityManager.merge(active);
        return 1;
    }

    @Override
    public Active selectById(Integer id) {

        return entityManager.find(Active.class, id);
    }

    @Override
    public List<Active> selectAll() {
        final String hql = "FROM Active ORDER BY activityId";
        return entityManager
                .createQuery(hql, Active.class)
                .getResultList();
    }
}
