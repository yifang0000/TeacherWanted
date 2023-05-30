package com.example.teacherwanted.active.dao.impl;

import com.example.teacherwanted.active.dao.ActiveFavoriteDao;
import com.example.teacherwanted.active.model.ActiveFavorite;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActiveFavoriteDaoImpl implements ActiveFavoriteDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(ActiveFavorite activeFavorite) {
        entityManager.persist(activeFavorite);
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int deleteByIdAndMemId(Integer activityId, Integer memId) {
        Query query = entityManager.createQuery(
                "DELETE FROM ActiveFavorite WHERE activityId = :activityId AND memId = :memId");
        query.setParameter("activityId", activityId);
        query.setParameter("memId", memId);

        int deletedCount = query.executeUpdate();
        return deletedCount;
    }



    @Override
    public int update(ActiveFavorite pojo) {
        return 0;
    }

    @Override
    public ActiveFavorite selectById(Integer id) {
        return null;
    }

    @Override
    public List<ActiveFavorite> selectAll() {
        String customQuery = "FROM ActiveFavorite ORDER BY activityId";


        TypedQuery<ActiveFavorite> query = entityManager.createQuery(customQuery, ActiveFavorite.class);
        return query.getResultList();

    }


    @Override
    public List<ActiveFavorite> selectActiveFavoriteByMemberId(Integer memId) {
        TypedQuery<ActiveFavorite> query = entityManager.createQuery(
                "FROM ActiveFavorite WHERE memId = :memId",
                ActiveFavorite.class);
        query.setParameter("memId", memId);


        return query.getResultList();
    }
}
