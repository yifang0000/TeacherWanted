package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.model.Active;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ActiveDaoImpl implements ActiveDao {
    @PersistenceContext
    private EntityManager entityManager;

    //    前臺操作 開始
    @Override
    public List<Active> recommendActivities(String activityType) {
        String queryStr = "SELECT a FROM Active a " +
                "WHERE a.activityStatus = 1 ";

        if (activityType != null) {
            queryStr += "AND a.activityType = :type ";
        }

        queryStr += "ORDER BY a.activityDueTime ASC";

        TypedQuery<Active> query = entityManager.createQuery(queryStr, Active.class);

        if (activityType != null) {
            query.setParameter("type", activityType);
        }

        query.setMaxResults(5);

        return query.getResultList();
    }

    //    前臺操作 結束

    //    後臺操作 開始

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
    public int updateStatus(Active active) {
        entityManager.merge(active);
        return 1;
    }

    @Override
    public Active selectById(Integer id) {

        return entityManager.find(Active.class, id);
    }

    public List<Active> selectBackAll(String key, String activityType, Integer teaId) {
        if (key == null || key.isEmpty()) {
            key = "%";
        } else {
            key = "%" + key + "%";
        }

        if (activityType == null || activityType.isEmpty()) {
            activityType = null; // 或根據需要設置為默認值
        }

        String customQuery = "FROM Active WHERE activityName LIKE :keyword";

        if (activityType != null) {
            customQuery += " AND activityType = :type";
        }

        if (teaId != null) {
            customQuery += " AND teaId = :teaId";
        }

        customQuery += " ORDER BY activityId";

        TypedQuery<Active> query = entityManager.createQuery(customQuery, Active.class)
                .setParameter("keyword", key);

        if (activityType != null) {
            query.setParameter("type", activityType);
        }

        if (teaId != null) {
            query.setParameter("teaId", teaId);
        }

        return query.getResultList();
    }
    //    後臺操作 結束

    //    以下是測試用

}
