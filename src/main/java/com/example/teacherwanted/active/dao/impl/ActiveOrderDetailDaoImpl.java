package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActiveOrderDetailDaoImpl implements ActiveOrderDetailDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ActiveOrderDetail> findByActiveId(Integer activityId) {
        TypedQuery<ActiveOrderDetail> query;
        if (activityId != null) {
            query = entityManager.createQuery(
                    "SELECT aod FROM ActiveOrderDetail aod WHERE aod.activityId = :activityId",
                    ActiveOrderDetail.class);
            query.setParameter("activityId", activityId);
        } else {
            query = entityManager.createQuery("SELECT aod FROM ActiveOrderDetail aod", ActiveOrderDetail.class);
        }

        return query.getResultList();
    }

    @Override
    public int insert(ActiveOrderDetail activeOrderDetail) {
        entityManager.persist(activeOrderDetail);
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(ActiveOrderDetail pojo) {
        return 0;
    }

    @Override
    public ActiveOrderDetail selectById(Integer id) {
        return null;
    }

    @Override
    public List<ActiveOrderDetail> selectAll() {
        String customQuery = "FROM ActiveOrderDetail ORDER BY activityId";


        TypedQuery<ActiveOrderDetail> query = entityManager.createQuery(customQuery, ActiveOrderDetail.class);
        return query.getResultList();

    }



    public List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer memId) {
        TypedQuery<ActiveOrderDetail> query = entityManager.createQuery(
                "FROM ActiveOrderDetail WHERE memId = :memId",
                ActiveOrderDetail.class);
        query.setParameter("memId", memId);


        return query.getResultList();

    }
}
