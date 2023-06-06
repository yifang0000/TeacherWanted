package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.FavoriteCourseDao;
import com.example.teacherwanted.course.model.vo.FavoriteCourseVo;
import com.example.teacherwanted.course.model.vo.FavoriteTeacherVo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteCourseDaoImpl implements FavoriteCourseDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<FavoriteCourseVo> findAll() {
        String hql = "FROM  FavoriteCourseVo ORDER BY courseId";
        return (List<FavoriteCourseVo>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public FavoriteCourseVo getFavCourseById(Integer id) {
        return entityManager.find(FavoriteCourseVo.class, id);
    }

    @Override
    public List<FavoriteCourseVo> getFavCoursesByMemId(Integer memId) {
        String hql = "FROM FavoriteCourseVo cr WHERE cr.memId = :memId";
        TypedQuery<FavoriteCourseVo> query = entityManager.createQuery(hql, FavoriteCourseVo.class);
        query.setParameter("memId", memId);
        return query.getResultList();
    }

    @Override
    public int checkFavCourse(Integer memId, Integer courseId) {
        String hql = "SELECT ft FROM FavoriteCourseVo ft WHERE ft.memId = :memId AND ft.courseId = :courseId";
        TypedQuery<FavoriteCourseVo> query = entityManager.createQuery(hql, FavoriteCourseVo.class)
                .setParameter("memId", memId)
                .setParameter("courseId", courseId);
        List<FavoriteCourseVo> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void createFavCourse(FavoriteCourseVo favoriteCourse) {
        entityManager.persist(favoriteCourse);
    }

    @Override
    public void deleteFavCourse(Integer memId, Integer courseId) {
        String hql = "DELETE FROM FavoriteCourseVo ft WHERE ft.memId = :memId AND ft.courseId = :courseId";
        entityManager.createQuery(hql)
                .setParameter("memId", memId)
                .setParameter("courseId", courseId)
                .executeUpdate();
    }
}
