package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.CourseOrderDao;
import com.example.teacherwanted.course.model.vo.CourseCommentVo;
import com.example.teacherwanted.course.model.vo.CourseOrderDetailVo;
import com.example.teacherwanted.course.model.vo.CourseOrderVo;
import com.example.teacherwanted.course.model.vo.FavoriteCourseVo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CourseOrderDaoImpl implements CourseOrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CourseOrderVo> findAll() {
        String hql = "FROM  CourseOrderVo ORDER BY orderId";
        return (List<CourseOrderVo>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public CourseOrderVo getCourseOrderById(Integer id) {
        return null;
    }

    @Override
    public List<CourseOrderVo> getCourseOrdesByMemId(Integer memId) {
        String hql = "FROM CourseOrderVo cr WHERE cr.memId = :memId";
        TypedQuery<CourseOrderVo> query = entityManager.createQuery(hql, CourseOrderVo.class);
        query.setParameter("memId", memId);
        return query.getResultList();
    }

    @Override
    public void createCourseOrder(CourseOrderVo courseOrder) {

    }

    @Override
    public void updateCourseOrder(CourseOrderVo courseOrder) {

    }

    @Override
    public void deleteCourseOrder(Integer id) {

    }

    @Override
    public List<CourseOrderDetailVo> getFeedbackByCourseId(Integer courseId) {
        String hql = "FROM CourseOrderDetailVo cr WHERE cr.courseId = :courseId ORDER BY cr.orderDetailId DESC";
        TypedQuery<CourseOrderDetailVo> query = entityManager.createQuery(hql, CourseOrderDetailVo.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public CourseOrderDetailVo getOrderDetailById(Integer id){
        return entityManager.find(CourseOrderDetailVo.class, id);
    }

    @Override
    public void createFeedback(CourseOrderDetailVo courseOrderDetail) {
        entityManager.persist(courseOrderDetail);
    }

    @Override
    public void updateFeedback(CourseOrderDetailVo courseOrderDetail) {
        entityManager.merge(courseOrderDetail);
    }

    @Override
    public void deleteFeedback(Integer id) {
        entityManager.remove(getOrderDetailById(id));

    }
}
